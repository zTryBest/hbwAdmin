package cn.hbw.common.util;

import cn.hbw.common.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ClassName：cn.hbw.common.util.SecurityUtils
 * Description：security工具类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/9 14:03
 **/
@Slf4j
public class SecurityUtils {

    public static UserDetails getCurrentUser(){
     final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     if (authentication == null){
        throw new BadRequestException(HttpStatus.UNAUTHORIZED,"当前登录状态过期");
     }
     if (authentication.getPrincipal() instanceof UserDetails){
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         UserDetailsService service = SpringContextHolder.getBean(UserDetailsService.class);
         return service.loadUserByUsername(userDetails.getUsername());
     }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED,"找不到当前登录信息");
    }
}
