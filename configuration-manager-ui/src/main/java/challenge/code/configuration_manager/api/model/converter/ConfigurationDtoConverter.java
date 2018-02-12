package challenge.code.configuration_manager.api.model.converter;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationDtoConverter implements Converter<ConfigurationDto, ConfigurationDocument> {
    @Override
    public ConfigurationDocument convert(ConfigurationDto configurationDto) {
        ConfigurationDocument data = new ConfigurationDocument();
        data.setActive(configurationDto.getActive());
        data.setApplicationName(configurationDto.getApplicationName());
        data.setId(new ObjectId(configurationDto.getId()));
        data.setName(configurationDto.getName());
        data.setType(configurationDto.getType());
        data.setValue(configurationDto.getValue());
        return data;
    }
}
