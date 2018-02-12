package challenge.code.configuration_manager;

import challenge.code.configuration_manager.client.model.DataType;
import org.testcontainers.containers.GenericContainer;

import java.util.stream.Stream;

import static challenge.code.configuration_manager.TestData.Key.*;

public class TestData {
  private static final String CONNECTION_STRING_PATTERN = "mongodb://localhost:%s/test";

  private int mongoPort = 27017;
  private String mongoDockerImage = "mongo:3.6";

  private String applicationName = "SERVICE-A";
  private Integer refreshIntervalInMs = 500;

  public String getConnectionString(GenericContainer container) {
    return String.format(CONNECTION_STRING_PATTERN, container.getMappedPort(mongoPort));
  }

  public GenericContainer createMongoContainer() {
    return new GenericContainer(mongoDockerImage).withExposedPorts(mongoPort);
  }

  public Stream<Object[]> produceTestData() {
    return Stream.of(
      new Object[]{SITE_NAME.value, DataType.STRING, "trendyol", Boolean.TRUE},
      new Object[]{IS_BASKET_ENABLED.value, DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE},
      new Object[]{MAX_ITEM_COUNT.value, DataType.INTEGER, 50, Boolean.FALSE}
    );
  }

  public int getMongoPort() {
    return mongoPort;
  }

  public String getMongoDockerImage() {
    return mongoDockerImage;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public Integer getRefreshIntervalInMs() {
    return refreshIntervalInMs;
  }

  public enum Key {
    SITE_NAME("SiteName"), IS_BASKET_ENABLED("IsBasketEnabled"), MAX_ITEM_COUNT("MaxItemCount");

    public final String value;

    Key(String value) {
      this.value = value;
    }
  }
}
