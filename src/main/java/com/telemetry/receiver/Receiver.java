package com.telemetry.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Receiver {

  private static final int PACKET_SIZE = 2048;

  private final int port;

  private Receiver(final int port) {
    this.port = port;
  }

  public static Receiver create(final int port) {
    return new Receiver(port);
  }

  public void run() throws IOException {
    System.out.printf("Listening on udp:%s:%d%n",
                      InetAddress.getLocalHost().getHostAddress(), port);

    final byte[] receiveData = new byte[PACKET_SIZE];
    final DatagramSocket serverSocket = new DatagramSocket(port);

    final DatagramPacket receivePacket = new DatagramPacket(receiveData,
                                                            receiveData.length);

    int count = 0;
    while (count < 1000) {
      serverSocket.receive(receivePacket);
      final Path file = Paths.get(String.format("telemetry-data-%08d.bin", count));
      Files.write(file, receiveData);
      System.out.println("Received: " + receivePacket.getLength());
      System.out.println("Saved to: " + file.toAbsolutePath());
      count++;
    }
  }

}
