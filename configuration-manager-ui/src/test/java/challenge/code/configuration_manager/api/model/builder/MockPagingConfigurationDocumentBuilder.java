package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MockPagingConfigurationDocumentBuilder {
    @Autowired
    private MockConfigurationDocumentBuilder configurationDocumentBuilder;

    public Page<ConfigurationDocument> buildEmpty() {
        return new PageImpl<>(new ArrayList<>());
    }

    public Page<ConfigurationDocument> buildWithSingleElement() {
        final ArrayList<ConfigurationDocument> stockEntities = new ArrayList<>();
        stockEntities.add(configurationDocumentBuilder.buildDefault());
        return new PageImpl<>(stockEntities);
    }
}
