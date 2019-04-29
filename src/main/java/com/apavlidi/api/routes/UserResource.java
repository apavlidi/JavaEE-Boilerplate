package com.apavlidi.api.routes;

import com.apavlidi.domain.User;
import com.apavlidi.service.UserService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

  @GET
  @Path("msgBodyWriter/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public User getUserWithMsgBodyWriter(@PathParam("id") Long userId) {
    return userService.findUserById(userId);
  }

  @GET
  @Path("customMediaType/{id}")
  @Produces({MediaType.TEXT_PLAIN, "text/custom"})
  //User can send "Accept" key on the header and as value -> text/plain, text/custom
  public User getUserWithCustomType(@PathParam("id") Long userId) {
    return userService.findUserById(userId);
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