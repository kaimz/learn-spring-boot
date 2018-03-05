package com.wuwii.common.log;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/5 17:19</pre>
 */
@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.wuwii.common.log.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        return result;
    }

    @Pointcut("bean(sysUserServiceImpl) && args(userEntity,..)")
    public void userPointCut(SysUserEntity userEntity) {

    }

    @Before("userPointCut(userEntity)")
    public void validateUser(SysUserEntity userEntity) {
        // to handler args
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "rvt")
    public void logAfter(Object rvt) {
        System.out.println(Objects.toString(rvt));
    }
}
