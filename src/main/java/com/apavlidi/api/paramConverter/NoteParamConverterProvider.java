package com.apavlidi.api.paramConverter;

import com.apavlidi.domain.Note;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class NoteParamConverterProvider implements ParamConverterProvider {

  @Override
  public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
    if (Note.class.isAssignableFrom(aClass)) {
      return new ParamConverter<T>() {
        @Override
        public T fromString(String incomingString) {
          String appendToString = " appended from paramConverter";
          incomingString += appendToString;
          return aClass.cast(incomingString);
        }

        @Override
        public String toString(T t) {
          if (t != null) {
            return t.toString();
          }
          return null;
        }
      };
    }
    return null;
  }
}
