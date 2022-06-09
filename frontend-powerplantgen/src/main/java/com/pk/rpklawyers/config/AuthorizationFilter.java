package com.pk.rpklawyers.config;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import com.pk.rpklawyers.util.Constant;
import com.pk.rpklawyers.util.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	private ArrayList<LinkedHashMap<String, String >> roles;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it
	    Class<?> resourceClass = resourceInfo.getResourceClass();
	    List<Role> classRoles = extractRoles(resourceClass);

	    // Get the resource method which matches with the requested URL
	    // Extract the roles declared by it
	    Method resourceMethod = resourceInfo.getResourceMethod();
	    List<Role> methodRoles = extractRoles(resourceMethod);

	    try {

	    	String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	    			
	    	if (authorizationHeader != null && authorizationHeader.contains("Bearer")) {
	    			
	    		String jwtoken = authorizationHeader.substring("Bearer".length()).trim();
	    		jwtoken = jwtoken.replace("\"", "");
	    		
	    		//This line will throw an exception if it is not a signed JWS (as expected)
	    		Claims claims = Jwts.parser()         
	    			.setSigningKey(DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET))
	    			.parseClaimsJws(jwtoken).getBody();	
	    			roles = (ArrayList<LinkedHashMap<String, String>>) claims.get("Roles");
	        	
	        	// Check if the user is allowed to execute the method
	            // The method annotations override the class annotations
	            if (methodRoles.isEmpty()) {
	                checkPermissions(classRoles);
	            } else {
	                checkPermissions(methodRoles);
	            }
	    	}
	    } catch (Exception e) {
	            requestContext.abortWith(
	            Response.status(Response.Status.FORBIDDEN).build());
	    }
	}

	// Extract the roles from the annotated element
	private List<Role> extractRoles(AnnotatedElement annotatedElement) {
	        if (annotatedElement == null) {
	            return new ArrayList<Role>();
	        } else {
	        	JWTTokenNeeded secured = annotatedElement.getAnnotation(JWTTokenNeeded.class);
	            if (secured == null) {
	                return new ArrayList<Role>();
	            } else {
	                Role[] allowedRoles = secured.value();
	                return Arrays.asList(allowedRoles);
	            }
	        }
	}

	private boolean checkPermissions(List<Role> allowedRoles) throws Exception {
		// Check if the user contains one of the allowed roles
	    // Throw an Exception if the user has not permission to execute the method
		
		for(Role aRole : allowedRoles) {
			for(LinkedHashMap<String,String> userRole : roles) {
				if(userRole.get("roleCode").equalsIgnoreCase(aRole.toString())) {
					return true;
				}

			}
		}
		throw new Exception("Not Allowed to Execute Operation");
		
	}
	
}
