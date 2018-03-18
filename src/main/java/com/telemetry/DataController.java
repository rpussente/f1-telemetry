package com.telemetry;

import com.telemetry.model.Data;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

  private final DataService dataService;

  @Autowired
  public DataController(final DataService dataService) {
    this.dataService = dataService;
  }

  @RequestMapping("/all")
  public List<Data> all() {
    return dataService.get();
  }

  @RequestMapping("/distance")
  public List<Float> totalDistance() {
    return dataService.get().stream().map(Data::totalDistance).collect(Collectors.toList());
  }

  @RequestMapping("/peek")
  public Data peek() {
    final Data value = dataService.get().get(0);

    System.out.println(value.totalDistance());

    return value;
  }
}
