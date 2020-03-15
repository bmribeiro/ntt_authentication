package com.everis.security.clock.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DateFieldNotValidExceptionMapper implements ExceptionMapper<DateFieldNotValidException> {

	@Override
	public Response toResponse(DateFieldNotValidException arg0) {
		return Response.status(Status.NOT_FOUND).entity("DATE_FIELD_NOT_VALID").build();
	}
}