package com.pk.rpklawyers.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

public class CORSResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
	//	headers.add("Access-Control-Allow-Origin", "*"); //allows CORS requests only coming from podcastpedia.org		
	//	headers.add("Content-Type", "application/x-www-form-urlencoded");
	//	headers.add("Content-Type", "application/json");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");			
		headers.add("Access-Control-Expose-Headers", "Authorization, LoggedInUserResult");
		headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, Content-Type, Authorization, responseType, LoggedInUserResult, Origin, X-Auth-Token");
	}

}
