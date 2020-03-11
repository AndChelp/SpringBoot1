package first.exception;

import first.response.ErrorCode;
import first.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllExceptions(Exception ex) {
        Response response = Response.builder()
                .statusCode(ErrorCode.UNEXPECTED_EXCEPTION)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Response.builder()
                .statusCode(ErrorCode.VALIDATION_ERROR)
                .statusMsg("Method args are not valid")
                .validationErrorFields(
                        ValidationErrorFields.getFields(ex.getBindingResult().getFieldErrors())
                )

                .build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(Response.builder()
                .statusCode(ErrorCode.VALIDATION_ERROR)
                .statusMsg("JSON parsing error")
                .build(), status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFound(Exception ex) {
        Response response = Response.builder()
                .statusCode(ErrorCode.USER_NOT_FOUND)
                .statusMsg(ex.getMessage())
                .build();
        return ResponseEntity.ok(response);
    }
}
