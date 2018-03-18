package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class Kers {

  public static Kers from(final ByteBuffer buffer) {
    return new AutoValue_Kers(buffer.getFloat(), buffer.getFloat());
  }

  @JsonProperty("level")
  abstract float level();

  @JsonProperty("maxLevel")
  abstract float maxLevel();
}
