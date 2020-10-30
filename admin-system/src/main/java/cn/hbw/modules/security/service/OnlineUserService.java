package cn.hbw.modules.security.service;

import cn.hbw.common.util.RedisUtils;
import cn.hbw.modules.security.config.bean.SecurityProperties;
import cn.hbw.modules.security.service.dto.JwtUserDto;
import cn.hbw.modules.security.service.dto.OnlineUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName：cn.hbw.modules.security.service.OnlineUserService
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/27 15:50
 **/
@Slf4j
//@Service
@RequiredArgsConstructor
public class OnlineUserService {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    /**
     * 保存在线用户信息
     * @param jwtUserDto
     * @param token
     * @param request
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request){

    }

    public OnlineUserDto getOne(String key){
        return (OnlineUserDto) redisUtils.get(key);
    }
}
