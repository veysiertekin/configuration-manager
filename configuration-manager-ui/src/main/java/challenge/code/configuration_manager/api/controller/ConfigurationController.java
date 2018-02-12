package challenge.code.configuration_manager.api.controller;

import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.GetByApplicationNamePagingRequest;
import challenge.code.configuration_manager.api.model.request.GetConfigurationsPagingRequest;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static challenge.code.configuration_manager.api.controller.endpoint.ConfigurationEndpoint.*;

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

    @GetMapping(GET_CONFIGURATIONS_BY_APPLICATION_NAME)
    public @ResponseBody
    ResponseEntity<Page<ConfigurationDto>> getConfigurationsByApplicationName(@Valid GetByApplicationNamePagingRequest request) {
        PageRequest result = conversionService.convert(request, PageRequest.class);
        return new ResponseEntity<>(configurationService.getConfigurationsByApplicationName(request.getApplicationName(), result), HttpStatus.OK);
    }

    @PutMapping(UPDATE_CONFIGURATION)
    public @ResponseBody
    ResponseEntity<ConfigurationDto> updateConfiguration(@PathVariable("configurationId") String id,
                                                         @RequestBody @Valid SaveConfigurationRequest request) {
        return new ResponseEntity<>(configurationService.updateConfiguration(id, request), HttpStatus.OK);
    }

    @PostMapping(SAVE_CONFIGURATION)
    public @ResponseBody
    ResponseEntity<ConfigurationDto> saveConfiguration(@RequestBody @Valid SaveConfigurationRequest request) {
        return new ResponseEntity<>(configurationService.saveConfiguration(request), HttpStatus.OK);
    }
}
