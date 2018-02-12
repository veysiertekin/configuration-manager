package challenge.code.configuration_manager.client.model.document;

import challenge.code.configuration_manager.client.model.DataType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import static challenge.code.configuration_manager.client.impl.MongoDbConfigurationClient.DB_COLLECTION;

@Document(collection = DB_COLLECTION)
@CompoundIndexes({
        @CompoundIndex(name = "app_name_idx", def = "{'applicationName': 1, 'name': 1}", unique = true)
})
public class ConfigurationDocument {
    @Id
    private ObjectId id;

    private String applicationName;

    private String name;
    private DataType type;
    private Object value;
    private Boolean active;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
