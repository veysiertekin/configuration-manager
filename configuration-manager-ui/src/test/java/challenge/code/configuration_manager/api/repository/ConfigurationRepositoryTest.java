package challenge.code.configuration_manager.api.repository;

import challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder;
import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static challenge.code.configuration_manager.api.model.builder.MockConfigurationDocumentBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ConfigurationRepositoryTest {

    private static final int FIRST_PAGE = 0;
    private static final int PAGE_SIZE = 10;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private MockConfigurationDocumentBuilder configurationDocumentBuilder;

    @Before
    public void setUp() {
        cleanUpApplicationData();
    }

    @Test
    public void save_dummy_data() {
        ConfigurationDocument data = configurationDocumentBuilder.buildDefault();

        data = configurationRepository.save(data);
        assertThat(data)
                .hasFieldOrPropertyWithValue("applicationName", DUMMY_APPLICATION_NAME)
                .hasFieldOrPropertyWithValue("name", DUMMY_PROPERTY_NAME)
                .hasFieldOrPropertyWithValue("type", DUMMY_TYPE)
                .hasFieldOrPropertyWithValue("value", DUMMY_VALUE)
                .extracting(ConfigurationDocument::getId)
                .isNotNull();
    }

    @Test
    public void get_saved_dummy_data() {
        ConfigurationDocument data = configurationDocumentBuilder.buildDefault();
        data = configurationRepository.save(data);
        ConfigurationDocument result = configurationRepository.findOne(data.getId().toString());
        assertThat(result)
                .isEqualTo(data);
    }

    @Test
    public void get_by_application_name() {
        ConfigurationDocument data = configurationDocumentBuilder.buildDefault();
        data = configurationRepository.save(data);

        Pageable pageable = new PageRequest(FIRST_PAGE, PAGE_SIZE);
        Page<ConfigurationDocument> result = configurationRepository.findByApplicationNameLike(data.getApplicationName(), pageable);
        assertThat(result)
                .hasSize(1)
                .element(0)
                .isEqualTo(data);
    }

    @Test
    public void get_all_with_pagination() {
        ConfigurationDocument data = configurationDocumentBuilder.buildDefault();
        configurationRepository.save(data);

        Pageable pageable = new PageRequest(FIRST_PAGE, PAGE_SIZE);
        Page<ConfigurationDocument> result = configurationRepository.findAll(pageable);
        assertThat(result)
                .isNotEmpty();
        assertThat(result.getNumber())
                .isEqualTo(0);
        assertThat(result.getTotalPages())
                .isEqualTo(1);
        assertThat(result.getTotalElements())
                .isEqualTo(1);
    }

    private void cleanUpApplicationData() {
        configurationRepository.removeByApplicationName(DUMMY_APPLICATION_NAME);
    }
}
