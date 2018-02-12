package challenge.code.configuration_manager.dataclient.impl;

import challenge.code.configuration_manager.dataclient.DataClient;
import challenge.code.configuration_manager.dataclient.model.DataType;

public class MongoDbClient implements DataClient {
  private final String applicationName;

  public MongoDbClient(String applicationName, String connectionString) {
    this.applicationName = applicationName;
  }

  @Override
  public void putOrUpdate(String name, DataType dataType, Object value, Boolean active) {

  }
}
