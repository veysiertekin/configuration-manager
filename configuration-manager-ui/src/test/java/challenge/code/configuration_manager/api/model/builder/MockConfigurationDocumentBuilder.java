package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.DataType;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.springframework.stereotype.Component;

@Component
public class MockConfigurationDocumentBuilder {
    public static final String DUMMY_APPLICATION_NAME = "TestApp";
    public static final String DUMMY_PROPERTY_NAME = "TEST";
    public static final DataType DUMMY_TYPE = DataType.STRING;
    public static final String DUMMY_VALUE = "test";

    public ConfigurationDocument buildDefault() {
        ConfigurationDocument data = new ConfigurationDocument();
        data.setApplicationName(DUMMY_APPLICATION_NAME);
        data.setName(DUMMY_PROPERTY_NAME);
        data.setType(DUMMY_TYPE);
        data.setValue(DUMMY_VALUE);
        return data;
    }
}
