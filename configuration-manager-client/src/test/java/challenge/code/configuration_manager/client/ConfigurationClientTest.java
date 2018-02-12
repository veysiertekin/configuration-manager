package challenge.code.configuration_manager.client;

import challenge.code.configuration_manager.TestData;
import challenge.code.configuration_manager.client.model.DataType;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static challenge.code.configuration_manager.TestData.Key.IS_BASKET_ENABLED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ConfigurationClientTest {
  private static final TestData testData = new TestData();

  @ClassRule
  public static final GenericContainer mongo = testData.createMongoContainer();

  private ConfigurationClient configurationClient;

  @Before
  public void setUp() {
    configurationClient = testData.createMongoClient(mongo);
  }

  @Test
  public void put_and_get_test_data() {
    assertThat(configurationClient.putOrUpdate(IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE))
      .isTrue();

    ConfigurationDto data = configurationClient.get(IS_BASKET_ENABLED.value);

    assertThat(data)
      .hasFieldOrPropertyWithValue("name", IS_BASKET_ENABLED.value)
      .hasFieldOrPropertyWithValue("applicationName", testData.getApplicationName());
  }

  @Test
  public void putOrUpdateTestData() {
    testData.produceTestData().forEach(
      data ->
        assertThat(configurationClient.putOrUpdate((String) data[0], (DataType) data[1], data[2], (Boolean) data[3]))
          .isTrue()
    );
  }

  @Test
  public void put_or_update_should_fail_with_null_name() {
    assertThatThrownBy(() -> configurationClient.putOrUpdate(null, DataType.BOOLEAN, new Object(), true))
      .isInstanceOf(NullPointerException.class);
  }

  @Test
  public void put_or_update_should_fail_with_null_type() {
    assertThatThrownBy(() -> configurationClient.putOrUpdate("asd", null, new Object(), true))
      .isInstanceOf(NullPointerException.class);
  }

  @Test
  public void put_or_update_should_fail_with_null_active() {
    assertThatThrownBy(() -> configurationClient.putOrUpdate("sdf", DataType.BOOLEAN, new Object(), null))
      .isInstanceOf(NullPointerException.class);
  }
}
