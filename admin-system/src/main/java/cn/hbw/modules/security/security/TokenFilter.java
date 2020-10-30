package cn.hbw.modules.security.security;

import cn.hbw.common.exceptions.BadRequestException;
import cn.hbw.common.exceptions.EntityNotFoundException;
import cn.hbw.common.util.StringUtils;
import cn.hbw.modules.security.config.bean.SecurityProperties;
import cn.hbw.modules.security.service.OnlineUserService;
import cn.hbw.modules.security.service.dto.OnlineUserDto;
import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * ClassName：cn.hbw.modules.security.security.TokenFilter
 * Description：token 过滤器
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/27 14:33
 **/
@RequiredArgsConstructor
public class TokenFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = resolveToken(request);
        /**
         * token 为空不需要查 Redis
         */
        if (StringUtils.isNotBlank(token)){
            OnlineUserDto onlineUserDto = null;
            boolean cleanUserCache = false;
            try {
                onlineUserDto = onlineUserService.getOne(properties.getOnlineKey() + token);
            }catch (ExpiredJwtException e ){
                logger.error(e.getMessage());
                cleanUserCache = true;
            }finally {
                if (cleanUserCache || Objects.isNull(onlineUserDto)){

                }
            }
            if (onlineUserDto !=null && Strings.hasText(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                tokenProvider.checkRenewal(token);
            }

        }
        doFilter(servletRequest,servletResponse,filterChain);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(properties.getHeader());
        if (Strings.hasText(header) && header.startsWith(properties.getTokenStartWith())){
            header.replace(properties.getTokenStartWith(),"");
            return header;
        }
        else {
            logger.debug("非法token: {}", new BadRequestException(header));
            return null;
        }

    }
}
