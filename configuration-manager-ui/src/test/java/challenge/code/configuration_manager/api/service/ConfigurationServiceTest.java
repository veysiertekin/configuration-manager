package challenge.code.configuration_manager.api.service;

import challenge.code.configuration_manager.api.model.builder.MockPageRequestBuilder;
import challenge.code.configuration_manager.api.model.builder.MockPagingConfigurationDocumentBuilder;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
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

import static org.assertj.core.api.Assertions.assertThat;
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
}


