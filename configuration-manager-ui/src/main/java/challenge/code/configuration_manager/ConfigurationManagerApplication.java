package challenge.code.configuration_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("challenge.code.configuration_manager")
@SpringBootApplication
public class ConfigurationManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigurationManagerApplication.class, args);
    }
}
