package challenge.code.configuration_manager.api.model.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static challenge.code.configuration_manager.api.model.TestConstants.FIRST_PAGE_NUMBER;
import static challenge.code.configuration_manager.api.model.TestConstants.PAGE_SIZE;

@Component
public class MockPageRequestBuilder {
    public PageRequest buildValid() {
        return new PageRequest(FIRST_PAGE_NUMBER, PAGE_SIZE);
    }
}
