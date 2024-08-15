package ru.gb.timesheet.aspect;

import jakarta.persistence.criteria.Join;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j // Simple logging facade for java
@Aspect
@Component
public class LoggingAspect {

//    Before - до аспектируемого метода
//    After = AfterReturning + AfterThrowing - после аспектируемого метода
//    AfterThrowing - после выброса exception
//    AfterReturning
//    Around - вызывается вместо аспектируемого метода

    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
    public  void timesheetServiceMethodPointcut(){

    }

//    Pointcut - Точка входа в аспект
    @Before(value = "timesheetServiceMethodPointcut()")
    public void beforeTimesheetServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
//        Long id = (Long) jp.getArgs()[0];
        log.info("Before -> TimesheetService#{}", methodName);
    }

    @After(value = "timesheetServiceMethodPointcut()")
    public void afterTimesheetServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
//        Long id = (Long) jp.getArgs()[0];
        log.info("After -> TimesheetService#{}", methodName);
    }

    @AfterReturning(value = "timesheetServiceMethodPointcut()")
    public void afterReturningTimesheetServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
//        Long id = (Long) jp.getArgs()[0];
        log.info("AfterReturning -> TimesheetService#{}", methodName);
    }

    @AfterThrowing(value = "timesheetServiceMethodPointcut()", throwing = "ex")
    public void afterThrowingTimesheetServiceFindById(JoinPoint jp, Exception ex){
        String methodName = jp.getSignature().getName();
//        Long id = (Long) jp.getArgs()[0];
        log.info("AfterThrowing -> TimesheetService#{} -> {}", methodName, ex.getClass().getName());
    }


}
