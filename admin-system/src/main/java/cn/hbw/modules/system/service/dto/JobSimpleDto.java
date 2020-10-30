package cn.hbw.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：cn.hbw.modules.system.service.dto.JobSimpleDto
 * Description：工作的简单包装类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/28 21:00
 **/
@Data
@NoArgsConstructor
public class JobSimpleDto implements Serializable {
    private Integer id;
    private String name;
}
