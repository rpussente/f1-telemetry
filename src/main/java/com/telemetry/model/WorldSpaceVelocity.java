package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class WorldSpaceVelocity {

  public static WorldSpaceVelocity from(final ByteBuffer buffer) {
    return new AutoValue_WorldSpaceVelocity(
        buffer.getFloat(), buffer.getFloat(), buffer.getFloat());
  }

  @JsonProperty("x")
  abstract float x();

  @JsonProperty("y")
  abstract float y();

  @JsonProperty("z")
  abstract float z();
}
