package challenge.code.configuration_manager.api.model.converter;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SaveConfigurationRequestConverter implements Converter<SaveConfigurationRequest, ConfigurationDocument> {
    @Override
    public ConfigurationDocument convert(SaveConfigurationRequest saveConfigurationRequest) {
        ConfigurationDocument result = new ConfigurationDocument();
        result.setValue(saveConfigurationRequest.getValue());
        result.setType(saveConfigurationRequest.getType());
        result.setApplicationName(saveConfigurationRequest.getApplicationName());
        result.setName(saveConfigurationRequest.getName());
        result.setActive(saveConfigurationRequest.getActive());
        return result;
    }
}
