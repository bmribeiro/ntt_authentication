package com.everis.security.clock.boundary;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("clock")
@Produces({"application/json"})
public class ClockResource {

    @Inject
    private ClockService clockService;

    @GET
    public Response getCurrentTime(@QueryParam("zone") @DefaultValue("UTC") @Encoded String zone){
        LocalDateTime time = clockService.getCurrentTime(zone);
        return Response.status(Status.OK).entity(time).build();
    }

    @GET
    @Path("{field}")
    public Response getDateField(@QueryParam("zone") @DefaultValue("UTC") @Encoded String zone,
                            @PathParam("field") String field){
        Object time = clockService.getLocalTimeField(zone, field);
        return Response.status(Status.OK).entity(time).build();
    }
}