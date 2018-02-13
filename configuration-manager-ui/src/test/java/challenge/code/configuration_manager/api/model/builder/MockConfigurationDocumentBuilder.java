package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.DataType;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MockConfigurationDocumentBuilder {
    public static final String DUMMY_ID = "5a827d22490116316da26c26";
    public static final String DUMMY_APPLICATION_NAME = "TestApp";
    public static final String DUMMY_PROPERTY_NAME = "TEST";
    public static final DataType DUMMY_TYPE = DataType.STRING;
    public static final String DUMMY_VALUE = "test";
    public static final Boolean DUMMY_ACTIVE = true;

    public ConfigurationDocument buildDefault() {
        ConfigurationDocument data = new ConfigurationDocument();
        data.setId(new ObjectId(DUMMY_ID));
        data.setApplicationName(DUMMY_APPLICATION_NAME);
        data.setName(DUMMY_PROPERTY_NAME);
        data.setType(DUMMY_TYPE);
        data.setValue(DUMMY_VALUE);
        data.setActive(DUMMY_ACTIVE);
        return data;
    }
}
