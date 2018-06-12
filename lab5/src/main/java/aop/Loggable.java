package aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** @see {@link aop.AopLog#logMethodCall(ProceedingJoinPoint)} */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Loggable {
}
