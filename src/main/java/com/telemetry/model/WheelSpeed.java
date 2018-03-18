package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import java.nio.ByteBuffer;

@AutoValue
public abstract class WheelSpeed {

  public static WheelSpeed from(final ByteBuffer buffer) {
    return new AutoValue_WheelSpeed(
        buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.get());
  }

  @JsonProperty("rearLeft")
  abstract float rearLeft();

  @JsonProperty("rearRight")
  abstract float rearRight();

  @JsonProperty("frontLeft")
  abstract float frontLeft();

  @JsonProperty("frontRight")
  abstract float frontRight();
}
