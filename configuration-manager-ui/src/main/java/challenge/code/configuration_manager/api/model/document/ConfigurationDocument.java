package challenge.code.configuration_manager.api.model.document;

import challenge.code.configuration_manager.api.model.DataType;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document(collection = "configuration_data")
@CompoundIndexes({
        @CompoundIndex(name = "app_name_idx", def = "{'applicationName': 1, 'name': 1}", unique = true)
})
public class ConfigurationDocument {
    @Id
    private ObjectId id;

    @NotBlank
    @Indexed
    private String applicationName;

    @NotBlank
    private String name;

    @NotNull
    private DataType type;

    private Object value;

    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationDocument)) return false;
        ConfigurationDocument that = (ConfigurationDocument) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(applicationName, that.applicationName) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, applicationName, name, type, value, active);
    }
}
