package com.github.s8u.annoconfig.serializer;

import com.github.s8u.annoconfig.anno.ConfigDeserialize.ConfigDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationDeserializer implements ConfigDeserializer<Location> {

  @Override
  public Location deserialize(String value) {
    String[] split = value.split(", ");

    World world = Bukkit.getWorld(split[0]);
    double x = Double.parseDouble(split[1]);
    double y = Double.parseDouble(split[2]);
    double z = Double.parseDouble(split[3]);
    float yaw = Float.parseFloat(split[4]);
    float pitch = Float.parseFloat(split[5]);

    return new Location(world, x, y, z, yaw, pitch);
  }

}
