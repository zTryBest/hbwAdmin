package cn.hbw.modules.system.domain;

import cn.hbw.common.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName：cn.hbw.modules.system.domain.Menu
 * Description：菜单实体类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 16:29
 **/
@Data
public class Menu extends BaseEntity implements Serializable {
    private Integer menuId;

    private Integer pid;

    private Integer subCount;

    private Integer type;

    private String title;

    private String name;

    private String component;

    private Integer menuSort;

    private String icon;

    private String path;

    private String iFrame;

    private String cache;

    private String hidden;

    private String permission;
}
