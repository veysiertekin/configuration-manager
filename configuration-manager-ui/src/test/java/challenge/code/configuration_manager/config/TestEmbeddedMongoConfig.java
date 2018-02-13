package challenge.code.configuration_manager.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TestConfiguration
public class TestEmbeddedMongoConfig {
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        final TestEmbeddedMongoProperties testEmbeddedMongoProperties = new TestEmbeddedMongoProperties();
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(testEmbeddedMongoProperties.getHost());
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, testEmbeddedMongoProperties.getDatabase());
    }
}