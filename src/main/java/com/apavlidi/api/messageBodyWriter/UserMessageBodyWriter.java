package com.apavlidi.api.messageBodyWriter;

import com.apavlidi.domain.User;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class UserMessageBodyWriter implements MessageBodyWriter<User> {

  @Override
  public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations,
      MediaType mediaType) {
    return User.class.isAssignableFrom(aClass);
  }

  @Override
  public void writeTo(User user, Class<?> aClass, Type type, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream)
      throws IOException, WebApplicationException {
    outputStream.write(user.toString().getBytes());
  }

  @Override
  public long getSize(User user, Class<?> aClass, Type type, Annotation[] annotations,
      MediaType mediaType) {
    return -1;
  }

}
