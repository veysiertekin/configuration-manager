package challenge.code.configuration_manager.config;

public class TestEmbeddedMongoProperties {
    private String host = "localhost";

    private String database = "test";

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
