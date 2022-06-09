package com.pk.rpklawyers.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider

public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {
	public static final String ERROR_MESSAGE = "This request was no good.";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response toResponse(final InvalidRequestException e) {
		return Response.status(Response.Status.BAD_REQUEST).entity(ERROR_MESSAGE).build();
	}

}
