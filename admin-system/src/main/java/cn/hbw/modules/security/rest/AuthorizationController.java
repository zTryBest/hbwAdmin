package cn.hbw.modules.security.rest;

import cn.hbw.common.annotation.rest.AnonymousDeleteMapping;
import cn.hbw.common.annotation.rest.AnonymousGetMapping;
import cn.hbw.common.annotation.rest.AnonymousPostMapping;
import cn.hbw.common.exceptions.BadRequestException;
import cn.hbw.common.util.SecurityUtils;
import cn.hbw.logging.annotation.Log;
import cn.hbw.modules.security.config.bean.LoginProperties;
import cn.hbw.modules.security.security.TokenProvider;
import cn.hbw.modules.security.service.OnlineUserService;
import cn.hbw.modules.security.service.dto.AuthUserDto;
import cn.hbw.modules.security.service.dto.JwtUserDto;
import cn.hbw.modules.system.domain.User;
import cn.hbw.modules.system.service.dto.UserDto;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName：cn.hbw.modules.security.rest.AuthorizationController
 * Description：登录类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/4 13:10
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Api(tags = "系统： 系统管理接口")
public class AuthorizationController {
    private final LoginProperties loginProperties;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;


    @Log("用户登录")
    @AnonymousPostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request){
        String password = authUser.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authenticate = managerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成令牌
        String token = tokenProvider.createToken(authenticationToken);
        JwtUserDto principal =(JwtUserDto)  authenticate.getPrincipal();
        // 判断是否是单用户登录
        if (loginProperties.isSingleLogin()){
            onlineUserService.kickOut(authUser.getUsername());
        }
        // 保存在线信息
        onlineUserService.save(principal,token,request);
        // 返回 token 信息
        Map<String,Object> resultMap = new HashMap<String,Object>(2);
        resultMap.put("userInfo",principal);
        resultMap.put("token",token);
        return ResponseEntity.ok(resultMap);
    }

    @AnonymousPostMapping("/test")
    public ResponseEntity<Object> testJson(){
        throw new BadRequestException("fail");
    }

    @Log("获取用户信息")
    @GetMapping("/info")
    public ResponseEntity<Object> getCurrentUserInfo(){
        return ResponseEntity.ok(SecurityUtils.getCurrentUser() );
    }

    @Log("用户登出")
    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        onlineUserService.logOut(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
