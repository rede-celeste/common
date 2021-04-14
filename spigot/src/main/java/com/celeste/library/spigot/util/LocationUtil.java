package com.celeste.library.spigot.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public final class LocationUtil {

  /**
   * Serializes a location into a String
   * @param location Location
   * @param yawAndPitch boolean
   *
   * @return String
   */
  public static String serialize(@NotNull final Location location, final boolean yawAndPitch) {
    final StringJoiner joiner = new StringJoiner(";");

    joiner.add(location.getWorld().getName());
    joiner.add(String.valueOf(location.getX()));
    joiner.add(String.valueOf(location.getY()));
    joiner.add(String.valueOf(location.getZ()));
    joiner.add(String.valueOf(location.getY()));

    if (yawAndPitch) {
      joiner.add(String.valueOf(location.getYaw()));
      joiner.add(String.valueOf(location.getPitch()));
    }

    return joiner.toString().substring(0, joiner.length() - 1);
  }

  /**
   * Deserializes a location
   * @param serializedLocation String
   *
   * @return Location
   */
  public static Location deserialize(@NotNull final String serializedLocation) {
    final String[] location = serializedLocation.split(";");
    if (location.length == 4) {
      return new Location(
          Bukkit.getWorld(location[0]),
          Double.parseDouble(location[1]),
          Double.parseDouble(location[2]),
          Double.parseDouble(location[3])
      );
    }

    if (location.length == 6) {
      return new Location(
          Bukkit.getWorld(location[0]),
          Double.parseDouble(location[1]),
          Double.parseDouble(location[2]),
          Double.parseDouble(location[3]),
          Float.parseFloat(location[4]),
          Float.parseFloat(location[5])
      );
    }

    throw new IllegalArgumentException("This string cannot be deserialized");
  }

}
