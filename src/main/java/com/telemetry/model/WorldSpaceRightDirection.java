package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class WorldSpaceRightDirection {

  public static WorldSpaceRightDirection from(final ByteBuffer buffer) {
    return new AutoValue_WorldSpaceRightDirection(
        buffer.getFloat(), buffer.getFloat(), buffer.getFloat());
  }

  @JsonProperty("x")
  abstract float x();

  @JsonProperty("y")
  abstract float y();

  @JsonProperty("z")
  abstract float z();
}
