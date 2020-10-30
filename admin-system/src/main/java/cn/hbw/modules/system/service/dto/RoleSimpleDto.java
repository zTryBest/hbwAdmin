package cn.hbw.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName：cn.hbw.modules.system.service.dto.RoleSimpleDto
 * Description：Role 简单包装类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:24
 **/
@Data
@NoArgsConstructor
public class RoleSimpleDto {
    private Long roleId;

    private String name;

    private Integer level;

    private String dataScope;

    private List<MenuSimpleDto> menus;
}
