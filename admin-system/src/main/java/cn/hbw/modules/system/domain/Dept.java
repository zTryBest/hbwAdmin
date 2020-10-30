package cn.hbw.modules.system.domain;

import cn.hbw.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：cn.hbw.modules.system.domain.Dept
 * Description：部门实体类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:43
 **/
@Data
@NoArgsConstructor
public class Dept extends BaseEntity implements Serializable {

    private Long deptId;

    private Integer pid;

    private Integer subCount;

    private String name;

    private Integer deptSort;

    private String enabled;
}
