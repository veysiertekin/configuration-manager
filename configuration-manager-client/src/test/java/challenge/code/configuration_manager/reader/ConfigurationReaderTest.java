package challenge.code.configuration_manager.reader;

import challenge.code.configuration_manager.TestData;
import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.ConfigurationClientFactory;
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

  private ConfigurationClient configurationClient;

  @Before
  public void setUp() {
    configurationClient = ConfigurationClientFactory.createWithoutCache(testData.getApplicationName(), testData.getConnectionString(mongo));
  }

  @Test
  public void should_create_new() {
    ConfigurationReader reader = createReaderWithoutCache();
    assertThat(reader).isNotNull();
  }

  @Test
  public void should_get_without_cache() {
    final boolean saveSucceed = configurationClient.putOrUpdate(SITE_NAME.value, DataType.STRING, "trendyol", Boolean.TRUE);
    assertThat(saveSucceed).isTrue();

    ConfigurationReader reader = createReaderWithoutCache();
    String value = reader.getValue(SITE_NAME.value, String.class);
    assertThat(value).isEqualTo("trendyol");
  }

  @Test
  public void should_get_with_cache_provider() {
    final boolean saveSucceed = configurationClient.putOrUpdate(SITE_NAME.value, DataType.STRING, "trendyol", Boolean.TRUE);
    assertThat(saveSucceed).isTrue();

    ConfigurationReader reader = createReaderWithCaffeineCache();

    String value = reader.getValue(SITE_NAME.value, String.class);
    assertThat(value).isEqualTo("trendyol");
  }

  private ConfigurationReader createReaderWithoutCache() {
    return ConfigurationReaderFactory.createWithoutCache(
      testData.getApplicationName(),
      testData.getConnectionString(mongo)
    );
  }

  private ConfigurationReader createReaderWithCaffeineCache() {
    return ConfigurationReaderFactory.createWithCaffeine(
      testData.getApplicationName(),
      testData.getConnectionString(mongo),
      testData.getRefreshIntervalInMs()
    );
  }
}
