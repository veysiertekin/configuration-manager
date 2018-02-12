package challenge.code.configuration_manager;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationReaderTest {

  @Test
  void should_create_new() {
    ConfigurationReader reader = creationReader();
    assertThat(reader)
      .isNotNull();
  }

  private ConfigurationReader creationReader() {
    ConfigurationReaderFactory configurationReaderFactory = new ConfigurationReaderFactory();
    return configurationReaderFactory.createReader(TestConstants.APPLICATION_NAME, TestConstants.CONNECTION_STRING, TestConstants.REFRESH_INTERVAL_IN_MS);
  }
}
