package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import org.springframework.stereotype.Component;

import static challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder.*;

@Component
public class MockConfigurationDtoBuilder {
    public ConfigurationDto buildDefault() {
        ConfigurationDto data = new ConfigurationDto();
        data.setApplicationName(DUMMY_APPLICATION_NAME);
        data.setName(DUMMY_PROPERTY_NAME);
        data.setType(DUMMY_TYPE);
        data.setValue(DUMMY_VALUE);
        data.setActive(DUMMY_ACTIVE);
        return data;
    }
}
