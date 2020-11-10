package cn.hbw.logging.service.impl;

import cn.hbw.common.exceptions.BadRequestException;
import cn.hbw.common.util.SecurityUtils;
import cn.hbw.logging.domain.Log;
import cn.hbw.logging.mapper.LogMapper;
import cn.hbw.logging.service.LogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogMapper logMapper;
    @Async
    @Override
    public void save(String ip, ProceedingJoinPoint joinPoint, Log log) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        cn.hbw.logging.annotation.Log aopLog = method.getAnnotation(cn.hbw.logging.annotation.Log.class);
        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + method.getName() + "()";
        StringBuilder param = new StringBuilder();
        List<Object> objects = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        for (Object object : objects) {
            param.append(object).append(" ");
        }
        if (log!=null){
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setCreateTime(LocalDateTime.now());
        log.setRequestIp(ip);
        log.setLogType(aopLog.type().getValue());
        String username = getUsername();
        log.setUsername(username);
        logMapper.insert(log);
    }

    private String getUsername() {

        try{
           return SecurityUtils.getCurrentUser().getUsername();
        }catch (BadRequestException e){
            return "";
        }
    }
}
