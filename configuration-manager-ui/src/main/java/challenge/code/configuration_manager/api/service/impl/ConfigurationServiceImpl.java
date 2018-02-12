package challenge.code.configuration_manager.api.service.impl;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import challenge.code.configuration_manager.api.repository.ConfigurationRepository;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private Converter<ConfigurationDocument, ConfigurationDto> configurationDocumentConverter;

    @Override
    public Page<ConfigurationDto> getConfigurations(PageRequest request) {
        Page<ConfigurationDocument> result = configurationRepository.findAll(request);
        return result.map(configurationDocumentConverter);
    }

    @Override
    public Page<ConfigurationDto> getConfigurationsByApplicationName(String applicationName, PageRequest result) {
        Page<ConfigurationDocument> data = configurationRepository.findByApplicationName(applicationName, result);
        return data.map(configurationDocumentConverter);
    }

    @Override
    public ConfigurationDto updateConfiguration(String id, SaveConfigurationRequest request) {
        ConfigurationDocument document = conversionService.convert(request, ConfigurationDocument.class);
        document.setId(new ObjectId(id));
        document = configurationRepository.save(document);
        return conversionService.convert(document, ConfigurationDto.class);
    }

    @Override
    public ConfigurationDto saveConfiguration(SaveConfigurationRequest request) {
        ConfigurationDocument document = conversionService.convert(request, ConfigurationDocument.class);
        document = configurationRepository.save(document);
        return conversionService.convert(document, ConfigurationDto.class);
    }
}
