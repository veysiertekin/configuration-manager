package challenge.code.configuration_manager.api.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class GetConfigurationsPagingRequest {
    @NotNull(message = "'pageNumber' can not be empty!")
    @Min(value = 0, message = "'pageNumber' should be bigger than 0")
    private Integer pageNumber;

    @NotNull(message = "'pageSize' can not be empty!")
    @Min(value = 1, message = "'pageSize' should be bigger than 1")
    @Max(value = 250, message = "'pageSize' should be less than 250")
    private Integer pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetConfigurationsPagingRequest)) return false;
        GetConfigurationsPagingRequest that = (GetConfigurationsPagingRequest) o;
        return Objects.equals(pageNumber, that.pageNumber) &&
                Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pageNumber, pageSize);
    }
}
