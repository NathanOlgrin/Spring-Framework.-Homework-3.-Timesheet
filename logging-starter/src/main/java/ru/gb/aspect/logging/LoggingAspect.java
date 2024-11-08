package ru.gb.aspect.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j // Simple logging facade for java
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties properties;

//    Before - до аспектируемого метода
//    After = AfterReturning + AfterThrowing - после аспектируемого метода
//    AfterThrowing - после выброса exception
//    AfterReturning
//    Around - вызывается вместо аспектируемого метода

    @Pointcut("@annotation(ru.gb.timesheet.aspect.Logging)")
    public void loggingMethodsPointcut(){}

    @Pointcut("@within(ru.gb.timesheet.aspect.Logging)")
    public void loggingTypePointcut(){}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel().slf4j()).log("Before -> TimesheetService#{}", methodName);
        try{
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel().slf4j()).log("After -> TimesheetService#{}", methodName);
        }
    }


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
