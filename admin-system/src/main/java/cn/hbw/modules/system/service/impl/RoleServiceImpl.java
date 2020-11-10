package cn.hbw.modules.system.service.impl;

import cn.hbw.common.Constants;
import cn.hbw.common.util.HbwConstant;
import cn.hbw.common.util.StringUtils;
import cn.hbw.modules.system.mapper.MenuMapper;
import cn.hbw.modules.system.mapper.RoleMapper;
import cn.hbw.modules.system.service.RoleService;
import cn.hbw.modules.system.service.dto.MenuSimpleDto;
import cn.hbw.modules.system.service.dto.RoleSimpleDto;
import cn.hbw.modules.system.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName：cn.hbw.modules.system.service.impl.RoleServiceImpl
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 14:20
 **/
@Slf4j
@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    @Override
    public List<RoleSimpleDto> findSimpleDtoByUseId(Long id) {
        return roleMapper.selectSimpleDtoByUserId(id);
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto) {
        Set<String> permissions = new HashSet<>();
        // 获取角色集合
        List<RoleSimpleDto> roles = userDto.getRoles();
        List<RoleSimpleDto> collect = roles.stream().filter(item -> HbwConstant.ADMIN.equals(item.getName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)){
            permissions.add(HbwConstant.ADMIN);
            return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(MenuSimpleDto::getPermission).collect(Collectors.toSet());

         return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }
}
