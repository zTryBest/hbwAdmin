package cn.hbw.common.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @ClassName BadRequestException
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
public class BadRequestException extends RuntimeException{

    private Integer status =  BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }

}
