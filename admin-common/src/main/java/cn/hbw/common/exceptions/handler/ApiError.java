package cn.hbw.common.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName ApiError
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
public class ApiError {
    private Integer status = 400;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String msg;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMsg(message);
        return apiError;
    }
    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMsg(message);
        return apiError;
    }

}
