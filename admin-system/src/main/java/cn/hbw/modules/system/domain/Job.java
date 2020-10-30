package cn.hbw.modules.system.domain;

import cn.hbw.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Job
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@Data
@TableName("hbw_job")
@NoArgsConstructor
public class Job extends BaseEntity implements Serializable {

  private Long jobId;

  private String name;

  private Integer jobSort;

  private Boolean enabled;

}
