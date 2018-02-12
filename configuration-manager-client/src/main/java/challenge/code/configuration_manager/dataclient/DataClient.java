package challenge.code.configuration_manager.dataclient;

import challenge.code.configuration_manager.dataclient.model.DataType;

public interface DataClient {
  void putOrUpdate(String name, DataType dataType, Object value, Boolean active);
}
