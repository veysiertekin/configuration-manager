package challenge.code.configuration_manager.api.service;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ConfigurationService {
    Page<ConfigurationDto> getConfigurations(PageRequest request);

    ConfigurationDto saveConfiguration(SaveConfigurationRequest request);

    ConfigurationDto updateConfiguration(String id, SaveConfigurationRequest request);

    Page<ConfigurationDto> getConfigurationsByApplicationName(String applicationName, PageRequest result);

    void deleteConfiguration(String id);
}
