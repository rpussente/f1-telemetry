package com.telemetry;

import com.telemetry.model.Data;
import com.telemetry.model.Fuel;
import com.telemetry.model.GForce;
import com.telemetry.model.Kers;
import com.telemetry.model.SuspensionPosition;
import com.telemetry.model.SuspensionVelocity;
import com.telemetry.model.WheelSpeed;
import com.telemetry.model.WorldSpaceForwardDirection;
import com.telemetry.model.WorldSpacePosition;
import com.telemetry.model.WorldSpaceRightDirection;
import com.telemetry.model.WorldSpaceVelocity;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class FileParser {

  public static List<Data> loadData() {
    final List<Data> lits = new ArrayList<>();
    int count = 0;
    while (count < 1000) {
      final InputStream stream =
          FileParser.class.getResourceAsStream(
              String.format("/data/telemetry-data-%08d.bin", count));

      final Data data;
      try {
        data = parseData(IOUtils.toByteArray(stream));
        lits.add(data);
      } catch (IOException e) {
        e.printStackTrace();
      }

      count++;
    }
    return lits;
  }

  private static Data parseData(final byte[] bytes) {
    final ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

    final float m_time = buffer.getFloat();
    final float m_lapTime = buffer.getFloat();
    final float m_lapDistance = buffer.getFloat();
    final float m_totalDistance = buffer.getFloat();

    final WorldSpacePosition worldSpacePosition = WorldSpacePosition.from(buffer);

    final float m_speed = buffer.getFloat();

    final WorldSpaceVelocity worldSpaceVelocity = WorldSpaceVelocity.from(buffer);

    final WorldSpaceRightDirection worldSpaceRightDirection = WorldSpaceRightDirection.from(buffer);

    final WorldSpaceForwardDirection worldSpaceForwardDirection =
        WorldSpaceForwardDirection.from(buffer);

    final SuspensionPosition suspensionPosition = SuspensionPosition.from(buffer);

    final SuspensionVelocity suspensionVelocity = SuspensionVelocity.from(buffer);

    final WheelSpeed wheelSpeed = WheelSpeed.from(buffer);

    final float m_throttle = buffer.getFloat();
    final float m_steer = buffer.getFloat();
    final float m_brake = buffer.getFloat();
    final float m_clutch = buffer.getFloat();
    final float m_gear = buffer.getFloat();

    final GForce gForce = GForce.from(buffer);

    final float m_lap = buffer.getFloat();
    final float m_engineRate = buffer.getFloat();
    final float m_sli_pro_native_support = buffer.getFloat();

    final float m_car_position = buffer.getFloat();

    final Kers kers = Kers.from(buffer);
    final float m_drs = buffer.getFloat();

    final float m_traction_control = buffer.getFloat();

    final float m_anti_lock_brakes = buffer.getFloat();
    final Fuel fuel = Fuel.from(buffer);

    final float m_in_pits = buffer.getFloat();

    final Data data =
        Data.create(
            m_time,
            m_lapTime,
            m_lapDistance,
            m_totalDistance,
            worldSpacePosition,
            m_speed,
            worldSpaceVelocity,
            worldSpaceRightDirection,
            worldSpaceForwardDirection,
            suspensionPosition,
            suspensionVelocity,
            wheelSpeed,
            m_throttle,
            m_steer,
            m_brake,
            m_clutch,
            m_gear,
            gForce,
            m_lap,
            m_engineRate,
            m_sli_pro_native_support,
            m_car_position,
            kers,
            m_drs,
            m_traction_control,
            m_anti_lock_brakes,
            fuel,
            m_in_pits);

    return data;


    //
    //    float m_sector;	// 0 = sector1, 1 = sector2, 2 = sector3
    //
    //    float m_sector1_time;	// time of sector1 (or 0)
    //
    //    float m_sector2_time;	// time of sector2 (or 0)
    //
    //    float m_brakes_temp[4];	// brakes temperature (centigrade)
    //
    //    float m_tyres_pressure[4];	// tyres pressure PSI
    //
    //    float m_team_info;	// team ID
    //
    //    float m_total_laps;	// total number of laps in this race
    //
    //    float m_track_size;	// track size meters
    //
    //    float m_last_lap_time;	// last lap time
    //
    //    float m_max_rpm;	// cars max RPM, at which point the rev limiter will kick in
    //
    //    float m_idle_rpm;	// cars idle RPM
    //
    //    float m_max_gears;	// maximum number of gears
    //
    //    float m_sessionType;	// 0 = unknown, 1 = practice, 2 = qualifying, 3 = race
    //
    //    float m_drsAllowed;	// 0 = not allowed, 1 = allowed, -1 = invalid / unknown
    //
    //    float m_track_number;	// -1 for unknown, 0-21 for tracks
    //
    //    float m_vehicleFIAFlags;	// -1 = invalid/unknown, 0 = none, 1 = green, 2 = blue, 3 =
    // yellow, 4 = red
    //
    //    float m_era;                    	// era, 2017 (modern) or 1980 (classic)
    //
    //    float m_engine_temperature;  	// engine temperature (centigrade)
    //
    //    float m_gforce_vert;	// vertical g-force component
    //
    //    float m_ang_vel_x;	// angular velocity x-component
    //
    //    float m_ang_vel_y;	// angular velocity y-component
    //
    //    float m_ang_vel_z;	// angular velocity z-component
    //
    //    byte  m_tyres_temperature[4];	// tyres temperature (centigrade)
    //
    //    byte  m_tyres_wear[4];	// tyre wear percentage
    //
    //    byte  m_tyre_compound;	// compound of tyre – 0 = ultra soft, 1 = super soft, 2 = soft, 3 =
    // medium, 4 = hard, 5 = inter, 6 = wet
    //
    //    byte  m_front_brake_bias;         // front brake bias (percentage)
    //
    //    byte  m_fuel_mix;                 // fuel mix - 0 = lean, 1 = standard, 2 = rich, 3 = max
    //
    //    byte  m_currentLapInvalid;    	// current lap invalid - 0 = valid, 1 = invalid
    //
    //    byte  m_tyres_damage[4];	// tyre damage (percentage)
    //
    //    byte  m_front_left_wing_damage;	// front left wing damage (percentage)
    //
    //    byte  m_front_right_wing_damage;	// front right wing damage (percentage)
    //
    //    byte  m_rear_wing_damage;	// rear wing damage (percentage)
    //
    //    byte  m_engine_damage;	// engine damage (percentage)
    //
    //    byte  m_gear_box_damage;	// gear box damage (percentage)
    //
    //    byte  m_exhaust_damage;	// exhaust damage (percentage)
    //
    //    byte  m_pit_limiter_status;	// pit limiter status – 0 = off, 1 = on
    //
    //    byte  m_pit_speed_limit;	// pit speed limit in mph
    //
    //    float m_session_time_left;  // NEW: time left in session in seconds
    //
    //    byte  m_rev_lights_percent;  // NEW: rev lights indicator (percentage)
    //
    //    byte  m_is_spectating;  // NEW: whether the player is spectating
    //
    //    byte  m_spectator_car_index;  // NEW: index of the car being spectated
    //
    //
    //
    //    // Car data
    //
    //    byte  m_num_cars;              	// number of cars in data
    //
    //    byte  m_player_car_index;        	// index of player's car in the array
    //
    //    CarUDPData  m_car_data[20];   // data for all cars on track
    //
    //
    //
    //    float m_yaw;  // NEW (v1.8)
    //
    //    float m_pitch;  // NEW (v1.8)
    //
    //    float m_roll;  // NEW (v1.8)
    //
    //    float m_x_local_velocity;          // NEW (v1.8) Velocity in local space
    //
    //    float m_y_local_velocity;          // NEW (v1.8) Velocity in local space
    //
    //    float m_z_local_velocity;          // NEW (v1.8) Velocity in local space
    //
    //    float m_susp_acceleration[4];   // NEW (v1.8) RL, RR, FL, FR
    //
    //    float m_ang_acc_x;                 // NEW (v1.8) angular acceleration x-component
    //
    //    float m_ang_acc_y;                 // NEW (v1.8) angular acceleration y-component
    //
    //    float m_ang_acc_z;                 // NEW (v1.8) angular acceleration z-component
    //
    //
    //  };
    //
    //
    //
    //  struct CarUDPData
    //
    //  {
    //
    //    float m_worldPosition[3]; // world co-ordinates of vehicle
    //
    //    float m_lastLapTime;
    //
    //    float m_currentLapTime;
    //
    //    float m_bestLapTime;
    //
    //    float m_sector1Time;
    //
    //    float m_sector2Time;
    //
    //    float m_lapDistance;
    //
    //    byte  m_driverId;
    //
    //    byte  m_teamId;
    //
    //    byte  m_carPosition;     // UPDATED: track positions of vehicle
    //
    //    byte  m_currentLapNum;
    //
    //    byte  m_tyreCompound;	// compound of tyre – 0 = ultra soft, 1 = super soft, 2 = soft, 3 =
    // medium, 4 = hard, 5 = inter, 6 = wet
    //
    //    byte  m_inPits;           // 0 = none, 1 = pitting, 2 = in pit area
    //
    //    byte  m_sector;           // 0 = sector1, 1 = sector2, 2 = sector3
    //
    //    byte  m_currentLapInvalid; // current lap invalid - 0 = valid, 1 = invalid
    //
    //    byte  m_penalties;  // NEW: accumulated time penalties in seconds to be added
    //
    //  };

  }
}
