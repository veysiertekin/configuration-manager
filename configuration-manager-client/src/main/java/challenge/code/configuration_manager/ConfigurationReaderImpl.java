package challenge.code.configuration_manager;

import java.io.Serializable;

public class ConfigurationReaderImpl implements ConfigurationReader {
    private final String applicationName;
    private final String connectionString;
    private final Integer refreshIntervalInMs;

    public ConfigurationReaderImpl(String applicationName, String connectionString, Integer refreshIntervalInMs) {
        this.applicationName = applicationName;
        this.connectionString = connectionString;
        this.refreshIntervalInMs = refreshIntervalInMs;
    }
}
