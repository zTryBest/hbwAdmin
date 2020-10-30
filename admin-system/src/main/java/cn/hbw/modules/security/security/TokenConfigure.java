package cn.hbw.modules.security.security;

import cn.hbw.modules.security.config.bean.SecurityProperties;
import cn.hbw.modules.security.service.OnlineUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;

/**
 * ClassName：cn.hbw.modules.security.security.TokenConfiguer
 * Description：token过滤器配置到过滤器链
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/30 10:31
 **/
@RequiredArgsConstructor
public class TokenConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        TokenFilter tokenFilter = new TokenFilter(tokenProvider, properties, onlineUserService);
        builder.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
