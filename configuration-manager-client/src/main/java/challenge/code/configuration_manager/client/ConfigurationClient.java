package challenge.code.configuration_manager.client;

import challenge.code.configuration_manager.client.model.DataType;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;

public interface ConfigurationClient {
  ConfigurationDto get(String name);

  boolean putOrUpdate(String name, DataType type, Object value, Boolean active);
}
