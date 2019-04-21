package com.apavlidi.api.routes;

import com.apavlidi.domain.User;
import com.apavlidi.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("limit") int limit) {
        List<User> allUsers = userService.findAllUsers();
        if (limit != 0) {
            return Response.ok().entity(allUsers.subList(0, limit)).build();
        } else {
            return Response.ok().entity(allUsers).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long userId) {
        User user = userService.findUserById(userId);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long userId) {
        userService.deleteUserById(userId);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(User user) {
        userService.persist(user);
        return Response.status(201).entity(user).build();
    }
}