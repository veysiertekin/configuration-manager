package challenge.code.configuration_manager.reader;

public class ConfigurationReaderFactory {
  public ConfigurationReader createReader(String applicationName, String connectionString, Integer refreshIntervalInMs) {
    return new ConfigurationReaderBuilder()
      .build()
      .setApplicationName(applicationName)
      .setConnectionString(connectionString)
      .setRefreshIntervalInMs(refreshIntervalInMs);
  }
}