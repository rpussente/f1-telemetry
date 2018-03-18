package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class Fuel {

  public static Fuel from(final ByteBuffer buffer) {
    return new AutoValue_Fuel(buffer.getFloat(), buffer.getFloat());
  }

  @JsonProperty("inTank")
  abstract float inTank();

  @JsonProperty("capacity")
  abstract float capacity();
}
