package challenge.code.configuration_manager.api.service.impl;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
  @Override
  public Page<ConfigurationDto> getConfigurations(GetConfigurationsPagingRequest request) {
    return null;
  }
}
