package challenge.code.configuration_manager.cache.caffeine;

import challenge.code.configuration_manager.client.model.dto.ConfigurationDto;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class CaffeineRefreshTask extends TimerTask {
  private static final Logger logger = LoggerFactory.getLogger(CaffeineRefreshTask.class);

  private final LoadingCache<String, ConfigurationDto> cache;

  public CaffeineRefreshTask(LoadingCache<String, ConfigurationDto> cache) {
    this.cache = cache;
  }

  @Override
  public void run() {
    try {
      this.cache.asMap().keySet().forEach(cache::refresh);
    } catch (Exception e) {
      logger.error("An error occur while refreshing", e);
    }
  }
}
