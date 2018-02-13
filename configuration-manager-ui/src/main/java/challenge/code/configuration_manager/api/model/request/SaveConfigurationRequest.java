package challenge.code.configuration_manager.api.model.request;

import challenge.code.configuration_manager.api.model.DataType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class SaveConfigurationRequest {
    @NotBlank(message = "'applicationName' can not be empty!")
    private String applicationName;

    @NotBlank(message = "'name' can not be empty!")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveConfigurationRequest)) return false;
        SaveConfigurationRequest that = (SaveConfigurationRequest) o;
        return Objects.equals(applicationName, that.applicationName) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {

        return Objects.hash(applicationName, name, type, value, active);
    }
}
