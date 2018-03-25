package com.telemetry;

import com.telemetry.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  private final DataService dataService;

  @Autowired
  public WebSocketController(final DataService dataService) {
    this.dataService = dataService;
  }

  @MessageMapping("/send")
  @SendTo("/topic/data")
  public Data send(final DataIndex index) {
    return dataService.get().get(index.getValue());
  }
}
