package challenge.code.configuration_manager.api.service.impl;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.repository.ConfigurationRepository;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private Converter<ConfigurationDocument, ConfigurationDto> configurationDocumentConverter;

    @Override
    public Page<ConfigurationDto> getConfigurations(PageRequest request) {
        Page<ConfigurationDocument> result = configurationRepository.findAll(request);
        return result.map(configurationDocumentConverter);
    }
}
