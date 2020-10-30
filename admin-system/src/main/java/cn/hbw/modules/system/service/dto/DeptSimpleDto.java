package cn.hbw.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：cn.hbw.modules.system.service.dto.DeptSimpleDto
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 15:28
 **/
@Data
@NoArgsConstructor
public class DeptSimpleDto implements Serializable {
    private Long id;
    private String name;
}
