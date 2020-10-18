package cn.hbw.common.exceptions;

import org.springframework.util.StringUtils;

/**
 * @ClassName EntityNotFoundException
 * @Description 无法找到实例异常
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class clazz, String filed, String val){
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(),filed,val));
    }

    private static String generateMessage(String entity, String filed, String val){
        return StringUtils.capitalize(entity)
                +" with " + filed + " " + val + " does not exist";
    }
}
