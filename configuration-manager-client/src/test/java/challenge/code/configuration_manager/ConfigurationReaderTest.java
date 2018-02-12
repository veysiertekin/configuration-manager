package challenge.code.configuration_manager;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationReaderTest {
  private String applicationName = "SERVICE-A";
  private String connectionString = "mongodb://db1.example.net:27017/test";
  private Integer refreshIntervalInMs = 500;

  @Test
  void should_create_new() {
    ConfigurationReaderFactory configurationReaderFactory = new ConfigurationReaderFactory();
    ConfigurationReader reader = configurationReaderFactory.createReader(applicationName, connectionString, refreshIntervalInMs);
    assertThat(reader)
      .isNotNull();
  }
}
