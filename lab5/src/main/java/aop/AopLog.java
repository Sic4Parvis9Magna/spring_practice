package aop;

import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@Log4j2
@Aspect
@Component
@FieldDefaults(level = PRIVATE)
public class AopLog {
    @Pointcut("@annotation(Loggable)")
    public void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object logMethodCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        String args = Arrays.toString(methodArgs);

        log.info(() -> String.format("Call method %s with args %s", methodName, args));

        Object result = thisJoinPoint.proceed();

        log.info(() -> String.format("Method %s with args %s has returned %s", methodName, args, result));

        return result;
    }
}
