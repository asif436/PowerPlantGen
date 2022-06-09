package com.pk.rpklawyers.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import org.apache.xmlbeans.impl.util.Base64;

import com.pk.rpklawyers.util.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * This filter verify the access permissions for a user based on username and
 * passowrd provided in request
 */
@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {



	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String BEARER_PROPERTY = "";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
			.entity("You cannot access this resource").build();
	private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
			.entity("Access blocked for all users !!").build();
	private String username;


	@Override
	public void filter(ContainerRequestContext requestContext) {

		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {

		        @Override
		        public Principal getUserPrincipal() {
		            return () -> username;
		        }

		    @Override
		    public boolean isUserInRole(String role) {
		        return true;
		    }

		    @Override
		    public boolean isSecure() {
		        return currentSecurityContext.isSecure();
		    }

		    @Override
		    public String getAuthenticationScheme() {
		        return AUTHENTICATION_SCHEME;
		    }
		});	
		
		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		try {
			//Key key = MacProvider.generateKey();
			boolean test = requestContext.getMethod().equals("OPTION");
		//	Key key = generalKey();
			if (authorizationHeader == null)  {
			//	throw new Exception();

			}
			
			if (authorizationHeader != null && authorizationHeader.contains("Bearer")) {
			
				String jwtoken = authorizationHeader.substring("Bearer".length()).trim();
				jwtoken = jwtoken.replace("\"", "");
				List<String> allElementsList = Arrays.asList(jwtoken.split("."));
				// String username = requestContext.getSecurityContext().getUserPrincipal().getName();
				// Jwts.parser().setSigningKey(key).parseClaimsJws(jwtoken).getBody().getSubject().equals(username);				
			    //This line will throw an exception if it is not a signed JWS (as expected)
				for (String element: allElementsList) {
					if (element.contains("token")) {
						String[] ele = element.split("token:");
						jwtoken = ele[1];
					}
				}
  
				Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET))
			       .parseClaimsJws(jwtoken).getBody();	
			    username = claims.getSubject();

				
			} 

//		} catch (IOException ioe) {
//			ioe.printStackTrace();
		} catch (Exception e) {
			// logger.severe("#### invalid token : " + token);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}

	}

	// Sample method to construct a JWT
	/*
	 * private String createJWT(String id, String issuer, String subject, long
	 * ttlMillis) {
	 * 
	 * //The JWT signature algorithm we will be using to sign the token
	 * SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 * 
	 * long nowMillis = System.currentTimeMillis(); Date now = new
	 * Date(nowMillis);
	 * 
	 * //We will sign our JWT with our ApiKey secret byte[] apiKeySecretBytes =
	 * DatatypeConverter.parseBase64Binary(apiKey.getSecret()); Key signingKey =
	 * new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 * 
	 * //Let's set the JWT Claims JwtBuilder builder = Jwts.builder().setId(id)
	 * .setIssuedAt(now) .setSubject(subject) .setIssuer(issuer)
	 * .signWith(signatureAlgorithm, signingKey);
	 * 
	 * //if it has been specified, let's add the expiration if (ttlMillis >= 0)
	 * { long expMillis = nowMillis + ttlMillis; Date exp = new Date(expMillis);
	 * builder.setExpiration(exp); }
	 * 
	 * //Builds the JWT and serializes it to a compact, URL-safe string return
	 * builder.compact(); }
	 * 
	 * 
	 * private void parseJWT(String jwt) {
	 * 
	 * 
	 * Claims claims = Jwts.parser()
	 * .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
	 * .parseClaimsJws(jwt).getBody(); System.out.println("ID: " +
	 * claims.getId()); System.out.println("Subject: " + claims.getSubject());
	 * System.out.println("Issuer: " + claims.getIssuer());
	 * System.out.println("Expiration: " + claims.getExpiration()); }
	 */

	private Key getKey() {
		FileInputStream is;
		Key key = null;
		try {
			is = new FileInputStream("C:\\development\\keystore\\keystore.keystore");

			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(is, "abc123".toCharArray());

			String alias = "lawyersscorecard.com";

			key = keystore.getKey(alias, "abc123".toCharArray());
			if (key instanceof PrivateKey) {
				// Get certificate of public key
				// Certificate cert = keystore.getCertificate(alias);

				// Get public key
				// PublicKey publicKey = cert.getPublicKey();

				// Return a key pair
				// new KeyPair(publicKey, (PrivateKey) key);
				return key;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	public SecretKey generalKey(){
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decode(stringKey.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
        return key;
    }
}
