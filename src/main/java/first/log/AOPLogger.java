package first.log;

import first.log.annotation.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AOPLogger {
    private Logger logger = LogManager.getLogger(AOPLogger.class);

    @Around("@annotation(annotationLog)")
    public Object around(ProceedingJoinPoint point, Log annotationLog) throws Throwable {
        switch (annotationLog.level()) {
            case INFO:
                logger.info(point.getSignature().toString());
                break;
            case DEBUG:
                logger.debug(point.getSignature().toString() + Arrays.toString(point.getArgs()));
                break;
            case ERROR:
//                logger.error();
                //System.out.println(point.getSignature());
                break;
        }

        return point.proceed();
    }


}

























