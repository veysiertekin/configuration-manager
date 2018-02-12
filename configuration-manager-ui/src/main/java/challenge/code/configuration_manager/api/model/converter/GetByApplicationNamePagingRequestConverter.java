package challenge.code.configuration_manager.api.model.converter;

import challenge.code.configuration_manager.api.model.request.GetByApplicationNamePagingRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetByApplicationNamePagingRequestConverter implements Converter<GetByApplicationNamePagingRequest, PageRequest> {
    @Override
    public PageRequest convert(GetByApplicationNamePagingRequest getByApplicationNamePagingRequest) {
        return new PageRequest(getByApplicationNamePagingRequest.getPageNumber(), getByApplicationNamePagingRequest.getPageSize());
    }
}
