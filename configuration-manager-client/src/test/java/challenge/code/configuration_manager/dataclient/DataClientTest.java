package challenge.code.configuration_manager.dataclient;

import challenge.code.configuration_manager.dataclient.impl.MongoDbClient;
import challenge.code.configuration_manager.dataclient.model.DataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static challenge.code.configuration_manager.TestConstants.APPLICATION_NAME;
import static challenge.code.configuration_manager.TestConstants.CONNECTION_STRING;

class DataClientTest {
  private DataClient dataClient;

  @BeforeEach
  void setUp() {
    dataClient = new MongoDbClient(APPLICATION_NAME, CONNECTION_STRING);
  }

  @ParameterizedTest
  @MethodSource("produceTestData")
  void putOrUpdateTestData(String name, DataType dataType, Object value, Boolean active) {
    dataClient.putOrUpdate(name, dataType, value, active);
  }

  static Stream<Arguments> produceTestData() {
    return Stream.of(
      Arguments.of("SiteName", DataType.STRING, "trendyol", Boolean.TRUE),
      Arguments.of("IsBasketEnabled", DataType.BOOLEAN, Boolean.TRUE, Boolean.TRUE),
      Arguments.of("MaxItemCount", DataType.INTEGER, 50, Boolean.FALSE)
    );
  }
}
