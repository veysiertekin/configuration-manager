package challenge.code.configuration_manager.client;

import challenge.code.configuration_manager.client.impl.MongoDbConfigurationClient;

public class ConfigurationClientFactory {
  public static ConfigurationClient createDefault(String applicationName, String connectionString) {
    return new MongoDbConfigurationClient(applicationName, connectionString);
  }
}
