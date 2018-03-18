package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class GForce {

  public static GForce from(final ByteBuffer buffer) {
    return new AutoValue_GForce(buffer.getFloat(), buffer.getFloat());
  }

  @JsonProperty("latitude")
  abstract float latitude();

  @JsonProperty("longitude")
  abstract float longitude();
}
