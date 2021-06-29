package com.github.s8u.annoconfig.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigDeserialize {

  Class<? extends ConfigDeserializer> value();

  interface ConfigDeserializer<T> {
    T deserialize(String value);
  }

}