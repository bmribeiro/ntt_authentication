package com.everis.security.user.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserStateNotValidMapper implements ExceptionMapper<UserStateNotValid> {

	@Override
	public Response toResponse(UserStateNotValid arg0) {
		return Response.status(Status.BAD_REQUEST).entity(arg0.getMessage()).build();
	}

}