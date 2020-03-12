package first.log;

import first.log.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPLogger {
    private Logger logger = LoggerFactory.getLogger(AOPLogger.class);

    @Around("@annotation(annotationLog)")
    public Object around(ProceedingJoinPoint point, Log annotationLog) throws Throwable {
        /*String extraMessage = annotationLog.extraMessage();
        if (!extraMessage.isEmpty()) {
            extraMessage = ", and extra message = " + extraMessage;
        }*/
        Object proceedingObject = null;
        switch (annotationLog.level()) {
            case ERROR:
                logError(point.getSignature(), getException(point));
                break;
            case WARN:
                logWarn(point.getSignature(), getException(point));
                break;
            case DEBUG:
                long startTime = System.currentTimeMillis();
                proceedingObject = point.proceed();
                long passedTime = System.currentTimeMillis() - startTime;
                logDebug(point.getSignature(), point.getArgs(), passedTime);
                break;
            case INFO:
                logInfo(point.getSignature(), point.getArgs());
                break;
        }
        return proceedingObject == null ? point.proceed() : proceedingObject;
    }

    private void logInfo(Signature signature, Object[] args) {
        logger.info("Method = {} executed with args = {}", signature, args);
    }

    private void logError(Signature method, Exception ex) {
        if (ex != null) {
            logger.error("Method = {} got exception with message = {}", method, ex.getMessage());
        } else {
            logger.error("Some exception in method = {}", method);
        }
    }

    private void logWarn(Signature method, Exception ex) {
        if (ex != null) {
            logger.warn("Handler = {} got exception in method = {} with message = {}", method.toShortString(), ex.getStackTrace()[0], ex.getMessage());
        } else {
            logger.warn("Some exception in method = {}", method);
        }
    }

    private void logDebug(Signature method, Object[] args, long passedTime) {
        logger.debug("Method = {} executed in = {}ms with args = {}", method, passedTime, args);
    }

    private Exception getException(ProceedingJoinPoint point) {
        for (Object arg : point.getArgs()) {
            if (arg instanceof Exception) {
                return (Exception) arg;
            }
        }
        return null;
    }

}

























