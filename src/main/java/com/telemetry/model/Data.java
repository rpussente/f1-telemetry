package com.telemetry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Data {

  public static Data create(
      final float time,
      final float lapTime,
      final float lapDistace,
      final float totalDistance,
      final WorldSpacePosition worldSpacePosition,
      final float speed,
      final WorldSpaceVelocity worldSpaceVelocity,
      final WorldSpaceRightDirection worldSpaceRightDirection,
      final WorldSpaceForwardDirection worldSpaceForwardDirection,
      final SuspensionPosition suspensionPosition,
      final SuspensionVelocity suspensionVelocity,
      final WheelSpeed wheelSpeed,
      final float throttle,
      final float steer,
      final float brake,
      final float clutch,
      final float gear,
      final GForce gForce,
      final float lap,
      final float engineRate,
      final float sliProNativeSupport,
      final float carPosition,
      final Kers kers,
      final float drs,
      final float tractionControl,
      final float antiLockBrakes,
      final Fuel fuel,
      final float inPits) {
    return new AutoValue_Data(
        time,
        lapTime,
        lapDistace,
        totalDistance,
        worldSpacePosition,
        speed,
        worldSpaceVelocity,
        worldSpaceRightDirection,
        worldSpaceForwardDirection,
        suspensionPosition,
        suspensionVelocity,
        wheelSpeed,
        throttle,
        steer,
        brake,
        clutch,
        gear,
        gForce,
        lap,
        engineRate,
        sliProNativeSupport,
        carPosition,
        kers,
        drs,
        tractionControl,
        antiLockBrakes,
        fuel,
        inPits);
  }

  @JsonProperty("time")
  public abstract float time();

  @JsonProperty("lapTime")
  public abstract float lapTime();

  @JsonProperty("lapDistance")
  public abstract float lapDistance();

  @JsonProperty("totalDistance")
  public abstract float totalDistance();

  /** World space position */
  @JsonProperty("worldSpacePosition")
  public abstract WorldSpacePosition worldSpacePosition();

  // Speed of car in MPH
  @JsonProperty("speed")
  public abstract float speed();

  /** Velocity in world space */
  @JsonProperty("worldSpaceVelocity")
  public abstract WorldSpaceVelocity worldSpaceVelocity();

  /** World space right direction */
  @JsonProperty("worldSpaceRightDirection")
  public abstract WorldSpaceRightDirection worldSpaceRightDirection();

  /** World space forward direction */
  @JsonProperty("worldSpaceForwardDirection")
  public abstract WorldSpaceForwardDirection worldSpaceForwardDirection();

  @JsonProperty("suspensionPosition")
  public abstract SuspensionPosition suspensionPosition();

  @JsonProperty("suspensionVelocity")
  public abstract SuspensionVelocity suspensionVelocity();

  @JsonProperty("wheelSpeed")
  public abstract WheelSpeed wheelSpeed();

  @JsonProperty("throttle")
  public abstract float throttle();

  @JsonProperty("steer")
  public abstract float steer();

  @JsonProperty("brake")
  public abstract float brake();

  @JsonProperty("clutch")
  public abstract float clutch();

  @JsonProperty("gear")
  public abstract float gear();

  @JsonProperty("gForce")
  public abstract GForce gForce();

  @JsonProperty("lap")
  public abstract float lap();

  @JsonProperty("engineRate")
  public abstract float engineRate();

  /** SLI Pro support */
  @JsonProperty("sliProNativeSupport")
  public abstract float sliProNativeSupport();

  @JsonProperty("carPosition")
  public abstract float carPosition();

  @JsonProperty("kers")
  public abstract Kers kers();

  /** 0 = off, 1 = on */
  @JsonProperty("drs")
  public abstract float drs();

  /** 0 (off) - 2 (high) */
  @JsonProperty("tractionControl")
  public abstract float tractionControl();

  /** 0 (off) - 1 (on) */
  @JsonProperty("antiLockBrakes")
  public abstract float antiLockBrakes();

  @JsonProperty("fuel")
  public abstract Fuel fuel();

  @JsonProperty("inPits")
  public abstract float inPits();
}
