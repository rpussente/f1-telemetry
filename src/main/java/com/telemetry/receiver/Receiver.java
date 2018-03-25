package com.telemetry.receiver;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.udp.UdpServer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Receiver implements Closeable {

  private static final int PORT = 20777;

  private static final int PACKET_SIZE = 2048;

  private final AtomicLong previous = new AtomicLong();
  private final AtomicLong counter = new AtomicLong();

  private final ExecutorService executor = Executors.newCachedThreadPool();

  @Scheduled(fixedDelay = 1000)
  public void publishStatus() {
    final long current = counter.get();
    System.out.println(current - previous.getAndSet(current) + "msgs/s");
  }

  @PostConstruct
  public Receiver start() {
    executor.submit(this::startServer);
    return this;
  }

  private void startServer() {
    final UdpServer server;
    try {
      server = UdpServer.create(InetAddress.getLocalHost().getHostAddress(), PORT);
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }

    server.startAndAwait(
        (in, out) -> {
          in.receive()
              .asByteBuffer()
              .subscribe(buffer -> executor.submit(() -> writeData(buffer, counter.getAndAdd(1))));
          return Flux.never();
        });
  }

  private void writeData(final ByteBuffer buffer, final long c) {
    final Path path = Paths.get(String.format("./data/telemetry-data-%08d.bin", c));

    try (final AsynchronousFileChannel fileChannel =
        AsynchronousFileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
      fileChannel.write(buffer, 0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void close() {
    executor.shutdown();
  }
}
