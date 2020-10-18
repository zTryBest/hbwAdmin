package cn.hbw.logging.annotation;

import cn.hbw.logging.annotation.type.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Log
 * @Description 配置注解形式获取日志
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";

    /**
     *  是否启用
     */
    boolean enable() default true;

    LogType type() default LogType.SELECT;
}
