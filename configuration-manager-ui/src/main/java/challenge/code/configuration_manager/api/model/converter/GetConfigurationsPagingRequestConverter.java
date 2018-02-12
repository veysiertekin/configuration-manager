package challenge.code.configuration_manager.api.model.converter;

import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetConfigurationsPagingRequestConverter implements Converter<GetConfigurationsPagingRequest, PageRequest> {
    @Override
    public PageRequest convert(GetConfigurationsPagingRequest request) {
        return new PageRequest(request.getPageNumber(), request.getPageSize());
    }
}
