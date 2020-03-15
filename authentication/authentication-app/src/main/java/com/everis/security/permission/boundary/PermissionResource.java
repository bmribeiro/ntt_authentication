package com.everis.security.permission.boundary;

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

import com.everis.security.permission.entity.Permission;

@Path("permissions")
@Produces(MediaType.APPLICATION_JSON)
public class PermissionResource {
    @Inject
    private PermissionService permissionService;

    @GET
    @Path("{id}")
    public Response findPermissionById(@PathParam("id") Long id){
        Permission permission = this.permissionService.getById(id);
        return Response.status(Status.OK).entity(permission).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPermission(Permission permission){
        Permission permissionCreated = this.permissionService.create(permission);
        return Response.status(Status.OK).entity(permissionCreated).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePermission(Permission permission){
        Permission permissionUpdated = this.permissionService.update(permission);
        return Response.status(Status.OK).entity(permissionUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePermission(@PathParam("id") Long id){
        Permission permissionRemoved = this.permissionService.deleteById(id);
        return Response.status(Status.OK).entity(permissionRemoved).build();
    }
}