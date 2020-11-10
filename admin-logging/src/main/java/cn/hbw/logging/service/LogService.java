package cn.hbw.logging.service;

import cn.hbw.logging.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @InterfaceName LogService
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public interface LogService {
    void save( String ip, ProceedingJoinPoint joinPoint, Log log);
}
