package challenge.code.configuration_manager.client;

import challenge.code.configuration_manager.cache.caffeine.CaffeineConfigurationClient;
import challenge.code.configuration_manager.client.impl.MongoDbConfigurationClient;

public class ConfigurationClientFactory {
  public static ConfigurationClient createWithoutCache(String applicationName, String connectionString) {
    return new MongoDbConfigurationClient(applicationName, connectionString);
  }

  public static ConfigurationClient createWithCaffeine(String applicationName, String connectionString, Integer refreshIntervalInMs) {
    return new CaffeineConfigurationClient(new MongoDbConfigurationClient(applicationName, connectionString), refreshIntervalInMs);
  }
}
