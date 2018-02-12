package challenge.code.configuration_manager.reader;

public class ConfigurationReaderFactory {
  public static ConfigurationReader createWithoutCache(String applicationName, String connectionString) {
    return new ConfigurationReaderBuilder()
      .build()
      .setApplicationName(applicationName)
      .setConnectionString(connectionString);
  }

  public static ConfigurationReader createWithCaffeine(String applicationName, String connectionString, Integer refreshIntervalInMs) {
    return new ConfigurationReaderBuilder()
      .buildWithCaffeine(refreshIntervalInMs)
      .setApplicationName(applicationName)
      .setConnectionString(connectionString);
  }
}
