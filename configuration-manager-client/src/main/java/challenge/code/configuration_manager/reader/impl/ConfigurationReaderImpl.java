package challenge.code.configuration_manager.reader.impl;

import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.reader.ConfigurationReader;

public class ConfigurationReaderImpl implements ConfigurationReader {
    private final ConfigurationClient configurationClient;

    public ConfigurationReaderImpl(ConfigurationClient configurationClient) {
        this.configurationClient = configurationClient;
    }

    @Override
    public <T> T getValue(String key, Class<T> klass) {
        ConfigurationDto data = configurationClient.get(key);
        return klass.cast(data.getValue());
    }
}
