package challenge.code.configuration_manager.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.validation.annotation.Validated;

@TestComponent
@Validated
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class TestEmbeddedMongoProperties {

    @NotBlank
    private String host;

    @NotBlank
    private String database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
