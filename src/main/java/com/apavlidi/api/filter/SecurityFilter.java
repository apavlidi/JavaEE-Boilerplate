package com.apavlidi.api.filter;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.Base64;

public class SecurityFilter implements ContainerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
  private static final String SECURE_URL_PREFIX = "secured";

  @Override
  public void filter(ContainerRequestContext containerRequestContext) {
    if (isEndpointSecured(containerRequestContext)) {
      List<String> authHeader = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER);
      if (authHeader != null && authHeader.size() > 0) {
        String authToken = authHeader.get(0);
        authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
        String decodedString = Base64.decodeAsString(authToken);
        StringTokenizer stringTokenizer = new StringTokenizer(decodedString, ":");
        String username = stringTokenizer.nextToken();
        String password = stringTokenizer.nextToken();
        if (isCredentialsValid(username, password)) {
          return;
        }
      }
    }

    denyAccess(containerRequestContext);
  }

  private void denyAccess(ContainerRequestContext containerRequestContext) {
    Response unauthorizedStatus = Response.status(UNAUTHORIZED)
        .entity("You have no permission!").build();
    containerRequestContext.abortWith(unauthorizedStatus);
  }

  private boolean isCredentialsValid(String username, String password) {
    return username.equals("test") && password.equals("test");
  }

  private boolean isEndpointSecured(ContainerRequestContext containerRequestContext) {
    return containerRequestContext.getUriInfo().getPath().contains(SECURE_URL_PREFIX);
  }
}
