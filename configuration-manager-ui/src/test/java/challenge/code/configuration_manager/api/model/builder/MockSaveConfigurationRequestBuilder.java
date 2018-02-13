package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.TestConstants;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import org.springframework.stereotype.Component;

@Component
public class MockSaveConfigurationRequestBuilder {
    public SaveConfigurationRequest buildValid() {
        SaveConfigurationRequest result = new SaveConfigurationRequest();
        result.setActive(TestConstants.DUMMY_ACTIVE);
        result.setApplicationName(TestConstants.DUMMY_APPLICATION_NAME);
        result.setName(TestConstants.DUMMY_PROPERTY_NAME);
        result.setType(TestConstants.DUMMY_TYPE);
        result.setValue(TestConstants.DUMMY_VALUE);
        return result;
    }
}
