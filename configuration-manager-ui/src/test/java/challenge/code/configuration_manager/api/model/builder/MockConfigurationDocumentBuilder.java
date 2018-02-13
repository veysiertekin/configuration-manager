package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.TestConstants;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MockConfigurationDocumentBuilder {

    public ConfigurationDocument buildDefault() {
        ConfigurationDocument data = new ConfigurationDocument();
        data.setId(new ObjectId(TestConstants.DUMMY_ID));
        data.setApplicationName(TestConstants.DUMMY_APPLICATION_NAME);
        data.setName(TestConstants.DUMMY_PROPERTY_NAME);
        data.setType(TestConstants.DUMMY_TYPE);
        data.setValue(TestConstants.DUMMY_VALUE);
        data.setActive(TestConstants.DUMMY_ACTIVE);
        return data;
    }
}
