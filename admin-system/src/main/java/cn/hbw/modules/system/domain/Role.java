package cn.hbw.modules.system.domain;

import cn.hbw.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：cn.hbw.modules.system.domain.Role
 * Description：角色实体
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:11
 **/
@Data
@NoArgsConstructor
@TableName("hbw_role")
public class Role extends BaseEntity implements Serializable {
    private Long roleId;

    private String name;

    private Integer level;

    private String description;

    private String dataScope;
}
