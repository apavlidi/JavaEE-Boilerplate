package com.apavlidi.api.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class SecureResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String secureMethod(){
    return "This Api needs login";
  }

}
