package challenge.code.configuration_manager.api.repository;

import challenge.code.configuration_manager.api.model.document.ConfigurationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConfigurationRepository extends MongoRepository<ConfigurationDocument, String> {
    List<ConfigurationDocument> removeByApplicationName(String applicationName);

    List<ConfigurationDocument> findByApplicationName(String applicationName);
}
