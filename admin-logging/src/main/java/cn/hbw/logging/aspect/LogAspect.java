package cn.hbw.logging.aspect;

import cn.hbw.common.util.RequestHolder;
import cn.hbw.common.util.StringUtils;
import cn.hbw.logging.domain.Log;
import cn.hbw.logging.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName LogAspect
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Component
@Aspect
public class LogAspect {
    private final LogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(LogService logService){
        this.logService = logService;
    }

    /**
     *  切入点
     */
    @Pointcut("@annotation(cn.hbw.logging.annotation.Log)")
    public void pointCut(){

    }

    /**
     *  配置环绕通知，使用在 切入点
     */
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save("aa", StringUtils.getIp(request),joinPoint,log);

        return  result;
    }
}
