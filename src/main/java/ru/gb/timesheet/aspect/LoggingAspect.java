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
        if(jp.getArgs().length!=0){
            String type = jp.getArgs()[0].getClass().getName();
            log.info("Before -> TimesheetService#{}({} = {})", jp.getSignature().getName(), type, jp.getArgs()[0]);
        } else {
            log.info("Before -> TimesheetService#{}", jp.getSignature().getName());
        }
    }

    @After(value = "timesheetServiceMethodPointcut()")
    public void afterTimesheetServiceFindById(JoinPoint jp){
        if(jp.getArgs().length!=0){
            String type = jp.getArgs()[0].getClass().getName();
            log.info("After -> TimesheetService#{}({} = {})", jp.getSignature().getName(), type, jp.getArgs()[0]);
        } else {
            log.info("After -> TimesheetService#{}", jp.getSignature().getName());
        }
    }

    @AfterReturning(value = "timesheetServiceMethodPointcut()")
    public void afterReturningTimesheetServiceFindById(JoinPoint jp){
        if(jp.getArgs().length!=0){
            String type = jp.getArgs()[0].getClass().getName();
            log.info("AfterReturning -> TimesheetService#{}({} = {})", jp.getSignature().getName(), type, jp.getArgs()[0]);
        } else {
            log.info("AfterReturning -> TimesheetService#{}", jp.getSignature().getName());
        }
    }

    @AfterThrowing(value = "timesheetServiceMethodPointcut()", throwing = "ex")
    public void afterThrowingTimesheetServiceFindById(JoinPoint jp, Exception ex){
        log.info("AfterThrowing -> TimesheetService#{} -> {}", jp.getSignature().getName(), ex.getClass().getName());
    }
}
