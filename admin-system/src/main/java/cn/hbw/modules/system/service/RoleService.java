package cn.hbw.modules.system.service;

import cn.hbw.modules.system.service.dto.RoleSimpleDto;
import cn.hbw.modules.system.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.service.RoleService
 * Description：角色业务类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:20
 **/
public interface RoleService {
    /**
     * 根据用户 id 查询角色简单包装类集合
     * @param id
     * @return
     */
    List<RoleSimpleDto> findSimpleDtoByUseId(Long id);

    /**
     * 查询该用户的权限信息
     * @param userDto
     * @return
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto);
}
