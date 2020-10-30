package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.Role;
import cn.hbw.modules.system.service.dto.RoleSimpleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.mapper.RoleMapper
 * Description：角色 mapper 类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:19
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 用户 id 查询角色简单包装类集合
     * @param id
     * @return
     */
    @Results(value = {
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "role_id", property = "menus", many = @Many(select = "cn.hbw.modules.system.mapper.MenuMapper.selectSimpleDtoByRoleId"))
    }
    )
    @Select("select * from hbw_role where role_id in" +
            " ( select hur.role_id from hbw_users_roles hur where hur.user_id = #{id} )")
    List<RoleSimpleDto> selectSimpleDtoByUserId(Long id);

    /**
     * 用户 id 查询角色
     * @param id
     * @return
     */
    @Select("select * from hbw_role where role_id in" +
            " ( select hur.role_id from hbw_users_roles hur where hur.user_id = #{id} )")
    List<Role> selectRoleByUserId(Long id);
}
