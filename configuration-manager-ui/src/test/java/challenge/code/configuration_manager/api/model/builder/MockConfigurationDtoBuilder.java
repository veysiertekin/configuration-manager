package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.TestConstants;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import org.springframework.stereotype.Component;

@Component
public class MockConfigurationDtoBuilder {
    public ConfigurationDto buildDefault() {
        ConfigurationDto data = new ConfigurationDto();
        data.setApplicationName(TestConstants.DUMMY_APPLICATION_NAME);
        data.setName(TestConstants.DUMMY_PROPERTY_NAME);
        data.setType(TestConstants.DUMMY_TYPE);
        data.setValue(TestConstants.DUMMY_VALUE);
        data.setActive(TestConstants.DUMMY_ACTIVE);
        return data;
    }
}
