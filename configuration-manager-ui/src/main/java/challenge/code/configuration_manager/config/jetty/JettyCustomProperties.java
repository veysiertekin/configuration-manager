package challenge.code.configuration_manager.config.jetty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@Validated
@ConfigurationProperties(prefix = "server.jetty")
public class JettyCustomProperties {

    @Min(200)
    @Max(1000)
    private Integer maxThreads = 200;

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }
}
