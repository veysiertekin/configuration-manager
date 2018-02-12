package challenge.code.configuration_manager.reader;

import challenge.code.configuration_manager.TestData;
import challenge.code.configuration_manager.client.impl.MongoDbConfigurationClient;
import challenge.code.configuration_manager.client.model.DataType;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static challenge.code.configuration_manager.TestData.Key.SITE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationReaderTest {
  private static final TestData testData = new TestData();

  @ClassRule
  public static final GenericContainer mongo = testData.createMongoContainer();

  private MongoDbConfigurationClient configurationClient;

  @Before
  public void setUp() {
    configurationClient = testData.createMongoClient(mongo);
  }

  @Test
  public void should_create_new() {
    ConfigurationReader reader = creationReader();
    assertThat(reader)
      .isNotNull();
  }

  @Test
  public void should_get_key() {
    assertThat(configurationClient.putOrUpdate(SITE_NAME.value, DataType.STRING, "trendyol", Boolean.TRUE))
      .isTrue();

    ConfigurationReader reader = creationReader();
    String value = reader.getValue(SITE_NAME.value, String.class);
    assertThat(value)
      .isEqualTo("trendyol");
  }

  private ConfigurationReader creationReader() {
    return ConfigurationReaderFactory.createReader(
      testData.getApplicationName(),
      testData.getConnectionString(mongo),
      testData.getRefreshIntervalInMs()
    );
  }
}
