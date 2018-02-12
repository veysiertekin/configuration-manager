package challenge.code.configuration_manager.api.controller;

import challenge.code.configuration_manager.api.model.builder.MockConfigurationDtoBuilder;
import challenge.code.configuration_manager.api.model.builder.MockPageRequestBuilder;
import challenge.code.configuration_manager.api.model.builder.MockSaveConfigurationRequestBuilder;
import challenge.code.configuration_manager.api.model.dto.ConfigurationDto;
import challenge.code.configuration_manager.api.model.request.SaveConfigurationRequest;
import challenge.code.configuration_manager.api.service.ConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static challenge.code.configuration_manager.api.model.TestConstants.*;
import static challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder.DUMMY_APPLICATION_NAME;
import static challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder.DUMMY_ID;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ConfigurationController.class)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class ConfigurationControllerTest {
    private static final String SAVE_CONFIGURATION = "/api/configurations";
    private static final String UPDATE_CONFIGURATION = "/api/configurations/%s";
    private static final String GET_CONFIGURATIONS = "/api/configurations?pageNumber=%s&pageSize=%s";
    private static final String GET_CONFIGURATIONS_BY_APPLICATION_NAME = "/api/configurations/by-application-name?applicationName=%s&pageNumber=%s&pageSize=%s";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigurationService configurationService;

    @Autowired
    private MockPageRequestBuilder pageRequestBuilder;

    @Autowired
    private MockConfigurationDtoBuilder configurationDtoBuilder;

    @Autowired
    private MockSaveConfigurationRequestBuilder saveConfigurationRequestBuilder;

    @Test
    public void get_configurations_should_fail_with_empty_page() throws Exception {
        mockMvc.perform(get(String.format(GET_CONFIGURATIONS, "", PAGE_SIZE)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("")));
    }


    @Test
    public void get_configurations_should_fail_with_empty_size() throws Exception {
        mockMvc.perform(get(String.format(GET_CONFIGURATIONS, FIRST_PAGE_NUMBER, "")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void get_configurations_should_fail_with_invalid_size() throws Exception {
        mockMvc.perform(get(String.format(GET_CONFIGURATIONS, FIRST_PAGE_NUMBER, INVALID_PAGE_SIZE)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void get_configurations_should_succeed_with_empty_result() throws Exception {
        final PageRequest pageRequest = pageRequestBuilder.buildValid();
        final PageImpl<ConfigurationDto> expectedResult = new PageImpl<>(new ArrayList<>());

        when(configurationService.getConfigurations(pageRequest)).thenReturn(expectedResult);

        mockMvc.perform(get(String.format(GET_CONFIGURATIONS, pageRequest.getPageNumber(), pageRequest.getPageSize())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"content\":[]")));

        verify(configurationService, times(1)).getConfigurations(pageRequest);
    }

    @Test
    public void getConfigurationsByApplicationName_should_succeed_with_empty_result() throws Exception {
        final PageRequest pageRequest = pageRequestBuilder.buildValid();
        final PageImpl<ConfigurationDto> expectedResult = new PageImpl<>(new ArrayList<>());

        when(configurationService.getConfigurationsByApplicationName(DUMMY_APPLICATION_NAME, pageRequest)).thenReturn(expectedResult);

        mockMvc.perform(get(String.format(GET_CONFIGURATIONS_BY_APPLICATION_NAME, DUMMY_APPLICATION_NAME, pageRequest.getPageNumber(), pageRequest.getPageSize())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"content\":[]")));

        verify(configurationService, times(1)).getConfigurationsByApplicationName(DUMMY_APPLICATION_NAME, pageRequest);
    }


    @Test
    public void updateConfiguration_should_succeed_with_valid_data() throws Exception {
        SaveConfigurationRequest request = saveConfigurationRequestBuilder.buildValid();
        ConfigurationDto result = configurationDtoBuilder.buildDefault();

        when(configurationService.updateConfiguration(DUMMY_ID, request)).thenReturn(result);

        mockMvc.perform(put(String.format(UPDATE_CONFIGURATION, DUMMY_ID))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));

        verify(configurationService, times(1)).updateConfiguration(DUMMY_ID, request);
    }


    @Test
    public void saveConfiguration_should_succeed_with_valid_data() throws Exception {
        SaveConfigurationRequest request = saveConfigurationRequestBuilder.buildValid();
        ConfigurationDto result = configurationDtoBuilder.buildDefault();

        when(configurationService.saveConfiguration(request)).thenReturn(result);

        mockMvc.perform(post(SAVE_CONFIGURATION)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));

        verify(configurationService, times(1)).saveConfiguration(request);
    }
}
