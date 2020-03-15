package com.everis.security.group.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.everis.security.group.entity.Group;

@Path("groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @Inject
    private GroupService groupService;

    @GET
    @Path("{id}")
    public Response findGroupById(@PathParam("id") Long id){
        Group group = this.groupService.getById(id);
        return Response.status(Status.OK).entity(group).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGroup(Group group){
        Group groupCreated = this.groupService.create(group);
        return Response.status(Status.OK).entity(groupCreated).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGroup(Group group){
        Group groupUpdated = this.groupService.update(group);
        return Response.status(Status.OK).entity(groupUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteGroup(@PathParam("id") Long id){
        Group groupRemoved = this.groupService.deleteById(id);
        return Response.status(Status.OK).entity(groupRemoved).build();
    }
}