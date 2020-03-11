package first.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import first.exception.ValidationErrorFields;
import first.model.UserAPI;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT) //Исключение всех Default значений из сериализации
public class Response {
    @Builder.Default
    @JsonInclude
    private int statusCode = 0; //Единственное доступное Default значение
    private String statusMsg;
    private int userCount;
    private List<UserAPI> userAPIList;
    private UserAPI userAPI;
    private List<ValidationErrorFields> validationErrorFields;
}
