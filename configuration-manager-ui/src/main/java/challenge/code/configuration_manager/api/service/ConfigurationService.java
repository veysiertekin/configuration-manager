package challenge.code.configuration_manager.api.service;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ConfigurationService {
  Page<ConfigurationDto> getConfigurations(PageRequest request);
}
