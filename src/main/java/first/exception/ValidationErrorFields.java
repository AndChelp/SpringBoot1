package first.exception;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorFields {
    private String field;
    private Object invalidValue;
    private String message;

    static List<ValidationErrorFields> getFields(List<FieldError> fieldErrors) {
        List<ValidationErrorFields> validationErrorFieldsList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            ValidationErrorFields errors = new ValidationErrorFields();
            errors.setField(fieldError.getField());
            errors.setInvalidValue(fieldError.getRejectedValue());
            errors.setMessage(fieldError.getDefaultMessage());
            validationErrorFieldsList.add(errors);
        }
        return validationErrorFieldsList;
    }

}
