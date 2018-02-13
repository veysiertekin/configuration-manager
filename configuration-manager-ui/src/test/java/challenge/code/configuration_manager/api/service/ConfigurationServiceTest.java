package challenge.code.configuration_manager.api.service;

import challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder;
import challenge.code.configuration_manager.api.model.builder.MockPageRequestBuilder;
import challenge.code.configuration_manager.api.model.builder.MockPagingConfigurationDocumentBuilder;
import challenge.code.configuration_manager.api.model.builder.MockSaveConfigurationRequestBuilder;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.error.PropertyAlreadyExistsException;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import challenge.code.configuration_manager.api.repository.ConfigurationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static challenge.code.configuration_manager.api.model.TestConstants.DUMMY_APPLICATION_NAME;
import static challenge.code.configuration_manager.api.model.TestConstants.DUMMY_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(properties = {"spring.data.mongodb.host=localhost"})
public class ConfigurationServiceTest {
    @Autowired
    private ConfigurationService configurationService;

    @MockBean
    private ConfigurationRepository configurationRepository;

    @Autowired
    private MockPageRequestBuilder pageRequestBuilder;

    @Autowired
    private MockConfigurationDocumentBuilder configurationDocumentBuilder;

    @Autowired
    private MockSaveConfigurationRequestBuilder saveConfigurationRequestBuilder;

    @Autowired
    private MockPagingConfigurationDocumentBuilder pagingConfigurationDocumentBuilder;

    @Autowired
    private Converter<ConfigurationDocument, ConfigurationDto> configurationDocumentConverter;

    private void assertThatPageLoadingProduceExpectedResult(PageRequest pageRequest, Page<ConfigurationDocument> expectedResponse) {
        Page<ConfigurationDto> response = expectedResponse.map(configurationDocumentConverter);

        when(configurationRepository.findAll(pageRequest)).thenReturn(expectedResponse);

        Page<ConfigurationDto> result = configurationService.getConfigurations(pageRequest);
        assertThat(result)
                .isEqualTo(response);

        verify(configurationRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void get_configurations_should_get_empty_list() {
        PageRequest pageRequest = pageRequestBuilder.buildValid();
        Page<ConfigurationDocument> expectedResult = pagingConfigurationDocumentBuilder.buildEmpty();
        assertThatPageLoadingProduceExpectedResult(pageRequest, expectedResult);
    }

    @Test
    public void get_configurations_should_get_one_entity() {
        PageRequest pageRequest = pageRequestBuilder.buildValid();
        Page<ConfigurationDocument> expectedResult = pagingConfigurationDocumentBuilder.buildWithSingleElement();
        assertThatPageLoadingProduceExpectedResult(pageRequest, expectedResult);
    }

    @Test
    public void get_configurations_by_application_name_should_get_one_entity() {
        PageRequest pageRequest = pageRequestBuilder.buildValid();
        Page<ConfigurationDocument> expectedResult = pagingConfigurationDocumentBuilder.buildWithSingleElement();
        Page<ConfigurationDto> response = expectedResult.map(configurationDocumentConverter);

        when(configurationRepository.findByApplicationNameLike(DUMMY_APPLICATION_NAME, pageRequest)).thenReturn(expectedResult);

        Page<ConfigurationDto> result = configurationService.getConfigurationsByApplicationName(DUMMY_APPLICATION_NAME, pageRequest);
        assertThat(result)
                .isEqualTo(response);

        verify(configurationRepository, times(1)).findByApplicationNameLike(DUMMY_APPLICATION_NAME, pageRequest);
    }

    @Test
    public void delete_configuration_by_id_should_succeed() {
        doNothing().when(configurationRepository).delete(DUMMY_ID);

        configurationService.deleteConfiguration(DUMMY_ID);

        verify(configurationRepository, times(1)).delete(DUMMY_ID);
    }

    @Test
    public void update_configuration_should_succeed_with_valid_data() {
        SaveConfigurationRequest updateRequest = saveConfigurationRequestBuilder.buildValid();
        ConfigurationDocument expectedResult = configurationDocumentBuilder.buildDefault();
        ConfigurationDto response = configurationDocumentConverter.convert(expectedResult);

        when(configurationRepository.save(expectedResult)).thenReturn(expectedResult);

        ConfigurationDto result = configurationService.updateConfiguration(DUMMY_ID, updateRequest);
        assertThat(result)
                .isEqualTo(response);

        verify(configurationRepository, times(1)).save(expectedResult);
    }


    @Test
    public void save_configuration_should_succeed_with_valid_data() {
        SaveConfigurationRequest saveRequest = saveConfigurationRequestBuilder.buildValid();
        ConfigurationDocument expectedResult = configurationDocumentBuilder.buildDefault();
        expectedResult.setId(null);
        ConfigurationDto response = configurationDocumentConverter.convert(expectedResult);

        when(configurationRepository.save(expectedResult)).thenReturn(expectedResult);

        ConfigurationDto result = configurationService.saveConfiguration(saveRequest);
        assertThat(result)
                .isEqualTo(response);

        verify(configurationRepository, times(1)).save(expectedResult);
    }

    @Test
    public void save_configuration_should_fail_with_duplicated_key() {
        SaveConfigurationRequest saveRequest = saveConfigurationRequestBuilder.buildValid();
        ConfigurationDocument expectedResult = configurationDocumentBuilder.buildDefault();
        expectedResult.setId(null);

        when(configurationRepository.save(expectedResult)).thenThrow(new PropertyAlreadyExistsException());

        assertThatThrownBy(() -> configurationService.saveConfiguration(saveRequest))
                .isInstanceOf(PropertyAlreadyExistsException.class);

        verify(configurationRepository, times(1)).save(expectedResult);
    }
}
