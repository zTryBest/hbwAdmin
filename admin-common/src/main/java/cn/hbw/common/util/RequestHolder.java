package cn.hbw.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName RequestHandler
 * @Description 获取请求 HttpServletRequest 对象
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public class RequestHolder {
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
