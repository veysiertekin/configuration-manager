package challenge.code.configuration_manager.client.impl;

import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.model.DataType;
import challenge.code.configuration_manager.client.model.builder.ObjectMapperFactory;
import challenge.code.configuration_manager.client.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;

public class MongoDbConfigurationClient implements ConfigurationClient {
  public static final String DB_COLLECTION = "configuration_data";

  private final String applicationName;
  private final MongoTemplate database;

  private final ObjectMapper objectMapper;

  public MongoDbConfigurationClient(String applicationName, String connectionString) {
    Objects.requireNonNull(applicationName);

    this.applicationName = applicationName;
    this.database = createDbClient(connectionString);
    this.objectMapper = ObjectMapperFactory.createMongoMapper();
  }

  @Override
  public ConfigurationDto get(String name) {
    Query query = getQuery(name);

    ConfigurationDocument result = database.findOne(query, ConfigurationDocument.class);
    return objectMapper.convertValue(result, ConfigurationDto.class);
  }

  @Override
  public boolean putOrUpdate(String name, DataType type, Object value, Boolean active) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(type);
    Objects.requireNonNull(active);

    Query query = getQuery(name);

    Update update = Update.update("applicationName", applicationName);
    update.set("name", name);
    update.set("type", type);
    update.set("value", value);
    update.set("active", active);

    UpdateResult result = database.upsert(query, update, ConfigurationDocument.class);
    return result.wasAcknowledged();
  }

  private Query getQuery(String name) {
    return new Query()
      .addCriteria(Criteria.where("applicationName").is(applicationName))
      .addCriteria(Criteria.where("name").is(name));
  }

  private MongoTemplate createDbClient(String connectionString) {
    MongoClientURI connection = new MongoClientURI(connectionString);
    return new MongoTemplate(new MongoClient(connection), connection.getDatabase());
  }

}
