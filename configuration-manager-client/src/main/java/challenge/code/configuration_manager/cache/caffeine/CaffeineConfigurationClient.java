package challenge.code.configuration_manager.cache.caffeine;

import challenge.code.configuration_manager.client.ConfigurationClient;
import challenge.code.configuration_manager.client.model.DataType;
import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Timer;

public class CaffeineConfigurationClient implements ConfigurationClient {
  private final LoadingCache<String, ConfigurationDto> cache;
  private final ConfigurationClient baseConfigurationClient;

  public CaffeineConfigurationClient(ConfigurationClient baseConfigurationClient, Integer refreshIntervalInMs) {
    this.baseConfigurationClient = baseConfigurationClient;
    cache = Caffeine.newBuilder()
      .build(this::load);

    this.setTimer(cache, refreshIntervalInMs);
  }

  @Override
  public ConfigurationDto get(String name) {
    return cache.get(name);
  }

  @Override
  public boolean putOrUpdate(String name, DataType type, Object value, Boolean active) {
    throw new UnsupportedOperationException("Unsupported putOrUpdate method in Caffeine cache!");
  }

  private ConfigurationDto load(String name) {
    return baseConfigurationClient.get(name);
  }

  private void setTimer(LoadingCache<String, ConfigurationDto> cache, Integer refreshIntervalInMs) {
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(new CaffeineRefreshTask(cache), 0, refreshIntervalInMs);
  }
}
