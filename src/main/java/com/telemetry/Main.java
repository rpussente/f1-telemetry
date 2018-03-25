package com.telemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {

  @Bean
  public DataService dataService() {
    return new DataService();
  }

  public static void main(final String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
