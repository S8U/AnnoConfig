package com.github.s8u.annoconfig.serializer;

import com.github.s8u.annoconfig.anno.ConfigSerialize.ConfigSerializer;
import org.bukkit.Location;

public class LocationSerializer implements ConfigSerializer<Location> {

  @Override
  public String serialize(Location value) {
    return value.getWorld().getName() + ", " + value.getX() + ", " + value.getY() + ", " + value.getZ() + ", " + value.getYaw() + ", " + value.getPitch();
  }

}
