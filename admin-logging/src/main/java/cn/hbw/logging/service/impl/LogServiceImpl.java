package cn.hbw.logging.service.impl;

import cn.hbw.logging.domain.Log;
import cn.hbw.logging.mapper.LogMapper;
import cn.hbw.logging.service.LogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
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
    @Override
    public void save(String aa, String ip, ProceedingJoinPoint joinPoint, Log log) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        cn.hbw.logging.annotation.Log aopLog = method.getAnnotation(cn.hbw.logging.annotation.Log.class);
        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + method.getName() + "()";
        StringBuilder param = new StringBuilder();
        List<Object> objects = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        for (Object object : objects) {
            param.append(objects).append(" ");
        }
        if (log!=null){
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setRequestIp(ip);
        log.setLogType(aopLog.type().getValue());
        logMapper.insert(log);
    }
}
