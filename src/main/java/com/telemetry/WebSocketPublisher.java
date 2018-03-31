package com.telemetry;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebSocketPublisher {

  private final DataService dataService;
  private final SimpMessagingTemplate template;
  private final AtomicInteger counter = new AtomicInteger(0);

  @Autowired
  public WebSocketPublisher(final DataService dataService, final SimpMessagingTemplate template) {
    this.dataService = dataService;
    this.template = template;
  }

  @Scheduled(fixedDelay = 1000)
  public void publishUpdates() {
    int l = counter.getAndIncrement();
    if (l >= dataService.get().size()) {
      l = 0;
      counter.set(0);
    }
    template.convertAndSend("/topic/test", dataService.get().get(l));
  }
}
