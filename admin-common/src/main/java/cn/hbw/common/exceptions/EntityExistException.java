package cn.hbw.common.exceptions;

import org.springframework.util.StringUtils;

/**
 * @ClassName EntityExsitException
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public class EntityExistException extends RuntimeException{

    public EntityExistException(Class clazz, String filed, String val){
        super(EntityExistException.generateMessage(clazz.getSimpleName(),filed,val));
    }

    private static String generateMessage(String entity, String filed, String val){
        return StringUtils.capitalize(entity)
                +" with " + filed + " " + val + " does not exist";
    }
}
