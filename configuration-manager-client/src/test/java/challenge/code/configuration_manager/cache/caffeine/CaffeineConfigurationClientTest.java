package challenge.code.configuration_manager.cache.caffeine;

import challenge.code.configuration_manager.TestData;
import challenge.code.configuration_manager.client.ConfigurationClient;
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

  private ConfigurationClient mongoClient;
  private ConfigurationClient caffeineClient;

  @Before
  public void setUp() {
    mongoClient = testData.createMongoClient(mongo);
    caffeineClient = new CaffeineConfigurationClient(mongoClient, testData.getRefreshIntervalInMs());
  }

  @Test
  public void get() {
    assertThat(mongoClient.putOrUpdate(IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE))
      .isTrue();

    ConfigurationDto value = caffeineClient.get(IS_BASKET_ENABLED.value);
    assertThat(value)
      .hasFieldOrPropertyWithValue("name", IS_BASKET_ENABLED.value)
      .hasFieldOrPropertyWithValue("type", DataType.BOOLEAN)
      .hasFieldOrPropertyWithValue("value", Boolean.TRUE)
      .hasFieldOrPropertyWithValue("active", Boolean.TRUE);
  }

  @Test
  public void put_or_update_should_fail_due_to_unsupported_method() {
    assertThatThrownBy(() -> caffeineClient.putOrUpdate(IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE))
      .isInstanceOf(UnsupportedOperationException.class);
  }
}
