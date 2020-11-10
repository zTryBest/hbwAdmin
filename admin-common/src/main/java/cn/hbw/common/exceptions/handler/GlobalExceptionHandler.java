package cn.hbw.common.exceptions.handler;

import cn.hbw.common.exceptions.BadRequestException;
import cn.hbw.common.exceptions.EntityExistException;
import cn.hbw.common.exceptions.EntityNotFoundException;
import cn.hbw.common.util.ThrowableUtils;
import io.netty.util.internal.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @ClassName GlobalExceptionHandler
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 不可知异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handlerException(Throwable e){
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(),e.getMessage()));
    }

    /**
     * 处理 EntityExist
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理 EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(NOT_FOUND.value(),e.getMessage()));
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if(msg.equals(message)){
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(ApiError.error(message));
    }


    public ResponseEntity<ApiError> buildResponseEntity(ApiError error){
        return  new ResponseEntity(error, HttpStatus.valueOf(error.getStatus()));
    }
    
}
