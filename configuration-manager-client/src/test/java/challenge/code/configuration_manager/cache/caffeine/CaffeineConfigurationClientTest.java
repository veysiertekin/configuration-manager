package challenge.code.configuration_manager.cache.caffeine;

import challenge.code.configuration_manager.TestData;
import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.ConfigurationClientFactory;
import challenge.code.configuration_manager.client.model.DataType;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static challenge.code.configuration_manager.TestData.Key.IS_BASKET_ENABLED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class CaffeineConfigurationClientTest {
  private static final TestData testData = new TestData();

  @ClassRule
  public static final GenericContainer mongo = testData.createMongoContainer();

  private ConfigurationClient clientWithoutCache;
  private ConfigurationClient cachedClient;

  @Before
  public void setUp() {
    clientWithoutCache = ConfigurationClientFactory.createWithoutCache(testData.getApplicationName(), testData.getConnectionString(mongo));
    cachedClient = ConfigurationClientFactory.createWithCaffeine(testData.getApplicationName(), testData.getConnectionString(mongo), testData.getRefreshIntervalInMs());
  }

  @Test
  public void get() {
    assertThat(clientWithoutCache.putOrUpdate(IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE))
      .isTrue();

    ConfigurationDto value = cachedClient.get(IS_BASKET_ENABLED.value);
    assertThat(value)
      .hasFieldOrPropertyWithValue("name", IS_BASKET_ENABLED.value)
      .hasFieldOrPropertyWithValue("type", DataType.BOOLEAN)
      .hasFieldOrPropertyWithValue("value", Boolean.TRUE)
      .hasFieldOrPropertyWithValue("active", Boolean.TRUE);
  }

  @Test
  public void put_or_update_should_fail_due_to_unsupported_method() {
    assertThatThrownBy(() -> cachedClient.putOrUpdate(IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE))
      .isInstanceOf(UnsupportedOperationException.class);
  }
}
