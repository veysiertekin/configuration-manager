package challenge.code.configuration_manager.config.jetty;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfigurator {
    private final JettyCustomProperties servletContainerProperties;

    @Autowired
    public JettyConfigurator(JettyCustomProperties servletContainerProperties) {
        this.servletContainerProperties = servletContainerProperties;
    }

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        final JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        factory.addServerCustomizers(server -> {
            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
            threadPool.setMaxThreads(servletContainerProperties.getMaxThreads());
        });
        return factory;
    }
}
