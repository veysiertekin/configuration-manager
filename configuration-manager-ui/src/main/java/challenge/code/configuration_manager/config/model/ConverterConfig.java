package challenge.code.configuration_manager.config.model;

import challenge.code.configuration_manager.api.model.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConverterConfig {
    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new ConfigurationDocumentConverter());
        service.addConverter(new ConfigurationDtoConverter());
        service.addConverter(new GetConfigurationsPagingRequestConverter());
        service.addConverter(new SaveConfigurationRequestConverter());
        service.addConverter(new GetByApplicationNamePagingRequestConverter());
        return service;
    }
}
