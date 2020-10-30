package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.Menu;
import cn.hbw.modules.system.service.dto.MenuSimpleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.mapper.MenuMapper
 * Description：菜单数据接口
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 20:10
 **/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过角色 id 查询菜单权限集合
     * @param id
     * @return
     */
    @Select("select * from hbw_menu hm where hm.menu_id in" +
            " ( select hrm.menu_id from hbw_roles_menus hrm where hrm.role_id = #{id} )")
    List<MenuSimpleDto> selectSimpleDtoByRoleId(Long id);
}
