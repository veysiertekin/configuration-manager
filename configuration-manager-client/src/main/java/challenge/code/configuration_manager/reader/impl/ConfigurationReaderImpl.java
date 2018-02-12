package challenge.code.configuration_manager.reader.impl;

import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.ConfigurationClientFactory;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.reader.ConfigurationReader;

public class ConfigurationReaderImpl implements ConfigurationReader {
    private final Integer refreshIntervalInMs;
    private final ConfigurationClient configurationClient;

    public ConfigurationReaderImpl(String applicationName, String connectionString, Integer refreshIntervalInMs) {
        this.refreshIntervalInMs = refreshIntervalInMs;
        this.configurationClient = ConfigurationClientFactory.createDefault(applicationName, connectionString);
    }

    @Override
    public <T> T getValue(String key, Class<T> klass) {
        ConfigurationDto data = configurationClient.get(key);
        return klass.cast(data.getValue());
    }
}
