package com.telemetry;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

  private static final int PORT = 20777;

  @Bean
  public DataService dataService() throws IOException {
    return new DataService();
  }

  public static void main(final String[] args) throws IOException {
    SpringApplication.run(Main.class, args);
    //    Receiver.create(PORT).run();
  }
}
