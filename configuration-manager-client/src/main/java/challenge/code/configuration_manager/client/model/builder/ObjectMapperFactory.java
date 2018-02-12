package challenge.code.configuration_manager.client.model.builder;

import challenge.code.configuration_manager.jackson.JacksonObjectIdDeserializer;
import challenge.code.configuration_manager.jackson.JacksonObjectIdSerializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;

public class ObjectMapperFactory {
  public static ObjectMapper createMongoMapper() {
    return new ObjectMapper()
      .registerModule(serializerModule());
  }

  private static SimpleModule serializerModule() {
    SimpleModule simpleModule = new SimpleModule("objectId", Version.unknownVersion());
    simpleModule.addSerializer(ObjectId.class, new JacksonObjectIdSerializer());
    simpleModule.addDeserializer(ObjectId.class, new JacksonObjectIdDeserializer());
    return simpleModule;
  }
}
