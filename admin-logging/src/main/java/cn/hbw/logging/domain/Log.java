package cn.hbw.logging.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName Log
 * @Description
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Data
@TableName("hbw_log")
@NoArgsConstructor
public class  Log implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    /** 描述 */
    private String description;

    /** 请求 Ip */
    private String requestIp;

    /** 日志类型 */
    private String logType;

    /** 请求耗时 */
    private Long time;

    /** 异常信息 */
    private byte[] exceptionDetail;

    /** 创建时间 */
    private LocalDateTime createTime;

    public Log(String logType, Long time){
        this.logType = logType;
        this.time = time;
    }
}
