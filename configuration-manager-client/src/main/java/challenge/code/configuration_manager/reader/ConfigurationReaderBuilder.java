package challenge.code.configuration_manager.reader;

import challenge.code.configuration_manager.reader.impl.ConfigurationReaderImpl;

public class ConfigurationReaderBuilder {
  public SetApplicationName build() {
    return applicationName -> connectionString -> refreshIntervalInMs ->
      new ConfigurationReaderImpl(applicationName, connectionString, refreshIntervalInMs);
  }

  public interface SetApplicationName {
    SetConnectionString setApplicationName(String applicationName);
  }

  public interface SetConnectionString {
    SetRefreshIntervals setConnectionString(String connectionString);
  }

  public interface SetRefreshIntervals {
    ConfigurationReader setRefreshIntervalInMs(Integer refreshIntervalInMs);
  }
}
