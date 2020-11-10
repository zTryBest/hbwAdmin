package cn.hbw.modules.security.service;

import cn.hbw.common.util.PageUtils;
import cn.hbw.common.util.RedisUtils;
import cn.hbw.common.util.StringUtils;
import cn.hbw.modules.security.config.bean.SecurityProperties;
import cn.hbw.modules.security.service.dto.JwtUserDto;
import cn.hbw.modules.security.service.dto.OnlineUserDto;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.PageUtil;
import com.alibaba.druid.sql.PagerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@Service
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
        String dept = jwtUserDto.getUser().getDept()==null?"":jwtUserDto.getUser().getDept().getName();
        String ip   = StringUtils.getIp(request);
        String ad   = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(),dept,ip,ad,token,new Date());
        redisUtils.set(properties.getOnlineKey()+token,onlineUserDto,properties.getTokenValidityInSeconds(), TimeUnit.SECONDS);
    }

    /**
     * 获取用户在线信息
     * @param key
     * @return
     */
    public OnlineUserDto getOne(String key){
        return (OnlineUserDto) redisUtils.get(key);
    }

    /**
     * 踢出之前已经登录的用户
     * @param username
     */
    public void kickOut(String username) {
        List<OnlineUserDto> onlineUserDtos = getAll(username);
        if (!CollectionUtils.isEmpty(onlineUserDtos)) {
            for (OnlineUserDto onlineUserDto : onlineUserDtos) {
                if (onlineUserDto.getUsername()!=null && onlineUserDto.getUsername().equals(username)){
                    redisUtils.del(properties.getOnlineKey() + onlineUserDto.getKey());
                }
            }
        }
    }

    public Map<String,Object> getAll(String filter, Pageable pageable){
         List<OnlineUserDto> all = getAll(filter);
         Map<String, Object> map = PageUtils.toPage(
                PageUtils.toPage(pageable.getPageNumber(), pageable.getPageSize(), all),
                all.size()
        );
         return map ;
    }

    /**
     * 通过用户名获取该用户所有在线信息
     * @param filter
     * @return
     */
    public List<OnlineUserDto> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        List<OnlineUserDto> onlineUserDtos = null;
        if (!CollectionUtils.isEmpty(keys)) {
            onlineUserDtos = new ArrayList<>(16);
            for (String key : keys) {
                OnlineUserDto onlineUserDto = null;
                try {
                    onlineUserDto = (OnlineUserDto) redisUtils.get(key);
                }catch (Exception e){
                    log.error("其他系统登录");
                }
                if (onlineUserDto!=null) {
                    if (StringUtils.isNotEmpty(filter) && onlineUserDto.getUsername().equals(filter)) {
                        onlineUserDtos.add(onlineUserDto);
                    } else {
                        onlineUserDtos.add(onlineUserDto);
                    }
                }
            }
            onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        }
        return onlineUserDtos;
    }

    /**
     * 用户退出登录
     * @param token
     */
    public void logOut(String token) {
        redisUtils.del(properties.getOnlineKey()+token);
    }

}
