package com.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Status {

  @JsonProperty("rate")
  public abstract long rate();

  @JsonProperty("total")
  public abstract long total();

  public static Status create(final long rate, final long count) {
    return new AutoValue_Status(rate, count);
  }
}
