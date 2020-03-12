package first.exception;

import first.log.Level;
import first.log.annotation.Log;
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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Log(level = Level.ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllExceptions(Exception ex) {
        Response response = Response.builder()
                .statusCode(ErrorCode.UNEXPECTED_EXCEPTION)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Log(level = Level.WARN)
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

    @Log(level = Level.ERROR)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Response.builder()
                .statusCode(ErrorCode.VALIDATION_ERROR)
                .statusMsg("JSON parsing error")
                .build(), status);
    }

    @Log(level = Level.WARN)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFound(Exception ex) {
        Response response = Response.builder()
                .statusCode(ErrorCode.USER_NOT_FOUND)
                .statusMsg(ex.getMessage())
                .build();
        return ResponseEntity.ok(response);
    }
}
