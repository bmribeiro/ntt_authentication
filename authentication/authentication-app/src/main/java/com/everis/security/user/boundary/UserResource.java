package com.everis.security.user.boundary;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.everis.security.user.entity.User;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("{id}")
    public Response findUserById(@PathParam("id") Long id){
        User user = this.userService.getById(id);
        return Response.status(Status.OK).entity(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewUser(User user){
        User userCreated = this.userService.createUser(user);
        return Response.status(Status.CREATED).entity(userCreated).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user){
        User userUpdated = this.userService.updateUser(user);
        return Response.status(Status.OK).entity(userUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        User userRemoved = this.userService.deleteById(id);
        return Response.status(Status.OK).entity(userRemoved).build();
    }

    @GET
    @Path("username/{username}")
    public Response findUserByUsername(@PathParam("username") String username){
        User user = this.userService.getUserByUsername(username);
        return Response.status(Status.OK).entity(user).build();
    }

    private List<String> convertPermissions(String permissionsStr) {
        List<String> permissions = new LinkedList<>();

        if (permissionsStr == null) {
            return permissions;
        }

        if (permissionsStr.contains(",")){
            for (String permission : permissionsStr.split(",")){
                permissions.add(permission);
            }
        }else{
            permissions.add(permissionsStr);
        }
        return permissions;
    }

    @GET
    public Response findUsers(@QueryParam("group") String group,
                     @QueryParam("permissions") final String permissions,
                     @QueryParam("pageSize") @DefaultValue(value = "0") final int pageSize,
                     @QueryParam("pageOffset") @DefaultValue(value = "0") final int pageOffset){
        List<User> users = this.userService.getUsers(group, convertPermissions(permissions), pageSize, pageOffset);
        return Response.status(Status.OK).entity(users).build();
    }
}