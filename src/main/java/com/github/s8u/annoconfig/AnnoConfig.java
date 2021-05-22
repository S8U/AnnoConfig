package com.github.s8u.annoconfig;

import com.github.s8u.annoconfig.anno.ConfigDeserialize;
import com.github.s8u.annoconfig.anno.ConfigExclude;
import com.github.s8u.annoconfig.anno.ConfigName;
import com.github.s8u.annoconfig.anno.ConfigSerialize;
import com.github.s8u.platformconfig.Config;
import com.github.s8u.platformconfig.ConfigFactory;
import java.io.File;
import java.lang.reflect.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@AllArgsConstructor
public class AnnoConfig {

  @Getter private File file;

  @SneakyThrows
  public AnnoConfig load() {
    Config config = ConfigFactory.createConfig(file).load();

    for (Field field : getClass().getDeclaredFields()) {
      if (field.getAnnotation(ConfigExclude.class) != null) continue;

      field.setAccessible(true);

      String name;
      if (field.getAnnotation(ConfigName.class) == null) {
        name = field.getName();
      } else {
        name = field.getAnnotation(ConfigName.class).value();
      }

      Object value = config.get(name);
      if (field.getAnnotation(ConfigDeserialize.class) != null) {
        value = field.getAnnotation(ConfigDeserialize.class).value().newInstance().deserialize(value);
      }

      if (value != null) {
        field.set(this, value);
      }
    }

    return this;
  }

  @SneakyThrows
  public void save() {
    Config config = ConfigFactory.createConfig(file).load();

    for (Field field : getClass().getDeclaredFields()) {
      if (field.getAnnotation(ConfigExclude.class) != null) continue;

      field.setAccessible(true);

      String name;
      if (field.getAnnotation(ConfigName.class) == null) {
        name = field.getName();
      } else {
        name = field.getAnnotation(ConfigName.class).value();
      }

      Object value = field.get(this);
      if (field.getAnnotation(ConfigSerialize.class) != null) {
        value = field.getAnnotation(ConfigSerialize.class).value().newInstance().serialize(value);
      }

      config.set(name, value);
    }

    config.save();
  }

}
