package challenge.code.configuration_manager.api.controller;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static challenge.code.configuration_manager.api.controller.endpoint.ConfigurationEndpoint.GET_CONFIGURATIONS;

@RestController
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping(GET_CONFIGURATIONS)
    public @ResponseBody
    ResponseEntity<Page<ConfigurationDto>> getConfigurations(@Valid GetConfigurationsPagingRequest request) {
        PageRequest result = conversionService.convert(request, PageRequest.class);
        return new ResponseEntity<>(configurationService.getConfigurations(result), HttpStatus.OK);
    }
}
