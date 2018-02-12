package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import org.springframework.stereotype.Component;

import static challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder.*;

@Component
public class MockSaveConfigurationRequestBuilder {
    public SaveConfigurationRequest buildValid() {
        SaveConfigurationRequest result = new SaveConfigurationRequest();
        result.setActive(DUMMY_ACTIVE);
        result.setApplicationName(DUMMY_APPLICATION_NAME);
        result.setName(DUMMY_PROPERTY_NAME);
        result.setType(DUMMY_TYPE);
        result.setValue(DUMMY_VALUE);
        return result;
    }
}
