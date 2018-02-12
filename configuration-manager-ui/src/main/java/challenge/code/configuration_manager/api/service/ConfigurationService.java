package challenge.code.configuration_manager.api.service;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import org.springframework.data.domain.Page;

public interface ConfigurationService {
  Page<ConfigurationDto> getConfigurations(GetConfigurationsPagingRequest request);
}
