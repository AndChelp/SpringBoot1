package first.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPLogger {
/*    private Logger log = Logger.getLogger(AOPLogger.class.getName());

    @Before(value = "within(test.controller.UsersController)")
    public void logDeleteUserBefore(JoinPoint joinPoint) {
        log.info("Execute " + joinPoint.getSignature().toString());
    }

    @AfterReturning(value = "within(test.controller.UsersController)", returning = "result")
    public void logReturn(ResponseEntity<ErrorResponse> result) {
        ErrorResponse response = result.getBody();
        if (response != null) {
            log.info(response.toString());
        }
    }

    @AfterReturning(value = "within(test.exception.GlobalExceptionHandler)", returning = "result")
    public void logExceptions(ResponseEntity<ErrorResponse> result) {
        ErrorResponse response = result.getBody();
        if (response != null) {
            log.warning("Caught error... " + response.toString());
        }
    }*/

}

























