package challenge.code.configuration_manager.api.model.converter;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationDocumentConverter implements Converter<ConfigurationDocument, ConfigurationDto> {
    @Override
    public ConfigurationDto convert(ConfigurationDocument configurationDocument) {
        ConfigurationDto data = new ConfigurationDto();
        data.setActive(configurationDocument.getActive());
        data.setApplicationName(configurationDocument.getApplicationName());
        if (configurationDocument.getId() != null) {
            data.setId(configurationDocument.getId().toString());
        }
        data.setName(configurationDocument.getName());
        data.setType(configurationDocument.getType());
        data.setValue(configurationDocument.getValue());
        return data;
    }
}
