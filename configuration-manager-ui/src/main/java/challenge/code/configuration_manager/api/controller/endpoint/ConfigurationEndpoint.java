package challenge.code.configuration_manager.api.controller.endpoint;

public class ConfigurationEndpoint {
    public static final String GET_CONFIGURATIONS = "/api/configurations";
    public static final String SAVE_CONFIGURATION = "/api/configurations";
    public static final String UPDATE_CONFIGURATION = "/api/configurations/{configurationId}";
    public static final String GET_CONFIGURATIONS_BY_APPLICATION_NAME = "/api/configurations/by-application-name";

    private ConfigurationEndpoint() {
    }
}
