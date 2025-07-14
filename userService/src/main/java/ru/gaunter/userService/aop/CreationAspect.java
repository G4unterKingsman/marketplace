package ru.gaunter.userService.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CreationAspect {


    @Pointcut("@annotation(loggable)")
    public void creationPointcut(Loggable loggable) {
    }

    @After(value = "creationPointcut(loggable)", argNames = "joinPoint,loggable")
    public void logAfterCreation(JoinPoint joinPoint, Loggable loggable) {
        String methodName = joinPoint.getSignature().getName();

        String message = String.format("%s: Метод %s успешно выполнен", loggable, methodName);

        log.info(message);
        ;
        log.debug("{} | Метод: {} | Параметры: {}",
                message, methodName, joinPoint.getArgs());
    }

}
