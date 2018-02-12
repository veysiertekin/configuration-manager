package challenge.code.configuration_manager.api.model.builder;

import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import org.springframework.stereotype.Component;

import static challenge.code.configuration_manager.api.model.TestConstants.FIRST_PAGE_NUMBER;
import static challenge.code.configuration_manager.api.model.TestConstants.PAGE_SIZE;

@Component
public class MockGetConfigurationsPagingRequestBuilder {
    public GetConfigurationsPagingRequest buildValid() {
        GetConfigurationsPagingRequest request = new GetConfigurationsPagingRequest();
        request.setPageNumber(FIRST_PAGE_NUMBER);
        request.setPageSize(PAGE_SIZE);
        return request;
    }
}
