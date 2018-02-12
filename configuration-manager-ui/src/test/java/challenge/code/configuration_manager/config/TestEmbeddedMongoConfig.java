package challenge.code.configuration_manager.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@TestConfiguration
public class TestEmbeddedMongoConfig {
    @Autowired
    private TestEmbeddedMongoProperties testMongoProperties;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(testMongoProperties.getHost());
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, testMongoProperties.getDatabase());
    }
}