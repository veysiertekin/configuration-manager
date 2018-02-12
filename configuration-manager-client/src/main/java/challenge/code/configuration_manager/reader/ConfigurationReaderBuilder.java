package challenge.code.configuration_manager.reader;

import challenge.code.configuration_manager.client.ConfigurationClientFactory;
import challenge.code.configuration_manager.reader.impl.ConfigurationReaderImpl;

public class ConfigurationReaderBuilder {
  public SetApplicationName build() {
    return applicationName -> connectionString ->
      new ConfigurationReaderImpl(ConfigurationClientFactory.createWithoutCache(applicationName, connectionString));
  }

  public SetApplicationName buildWithCaffeine(Integer refreshIntervalInMs) {
    return applicationName -> connectionString ->
      new ConfigurationReaderImpl(ConfigurationClientFactory.createWithCaffeine(applicationName, connectionString, refreshIntervalInMs));
  }

  public interface SetApplicationName {
    SetConnectionString setApplicationName(String applicationName);
  }

  public interface SetConnectionString {
    ConfigurationReader setConnectionString(String connectionString);
  }
}
