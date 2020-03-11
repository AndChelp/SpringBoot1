package first.exception;

import first.response.ErrorCode;
import first.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse response = new ErrorResponse(ErrorCode.UNEXPECTED_EXCEPTION, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
/*
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodsExceptions(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED, ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleParsingExceptions(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
*/

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(Exception ex) {
        ErrorResponse response = new ErrorResponse(ErrorCode.USER_NOT_FOUND, ex.getMessage());
        return ResponseEntity.ok(response);
    }
}
