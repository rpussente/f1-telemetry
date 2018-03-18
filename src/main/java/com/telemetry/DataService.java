package com.telemetry;

import com.telemetry.model.Data;
import java.util.List;

public class DataService {

  private final List<Data> data;

  public DataService() {
    data = FileParser.loadData();
  }

  public List<Data> get() {
    return data;
  }
}
