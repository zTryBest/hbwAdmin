package cn.hbw.modules.security.service;

import cn.hbw.common.exceptions.BadRequestException;
import cn.hbw.common.exceptions.EntityNotFoundException;
import cn.hbw.modules.security.config.bean.LoginProperties;
import cn.hbw.modules.security.service.dto.JwtUserDto;
import cn.hbw.modules.system.service.DataService;
import cn.hbw.modules.system.service.RoleService;
import cn.hbw.modules.system.service.UserService;
import cn.hbw.modules.system.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName：cn.hbw.modules.security.service.UserDetailsServiceImpl
 * Description：security 登录逻辑类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 15:03
 **/
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final DataService dataService;
    private final LoginProperties properties;

    public void setEnableCache(boolean enableCache){
        this.properties.setCacheEnable(enableCache);
    }

    /**
     * 用户信息缓存
     */
    static Map<String, JwtUserDto> userDtoCache = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean searchDb = true;
        JwtUserDto jwtUserDto = null;
        if (properties.isCacheEnable() && userDtoCache.containsKey(username)){
            jwtUserDto = userDtoCache.get(username);
            searchDb = false;
        }
        if (searchDb){
            UserDto userDto;
            try {
                 userDto = userService.selectUserDtoByUsername(username);
            }
            catch (EntityNotFoundException e){
                throw new UsernameNotFoundException("",e);
            }
            if (userDto == null){
                throw new UsernameNotFoundException("");
            }
            else {
                if (userDto.getLocked() != null && userDto.getLocked()){
                    throw new BadRequestException("账号被锁定！");
                }
               jwtUserDto = new JwtUserDto(
                        userDto,
                        dataService.getDeptIds(userDto),
                        roleService.mapToGrantedAuthorities(userDto)
               );
                userDtoCache.put(username,jwtUserDto);
            }

        }
        return jwtUserDto;
    }
}
