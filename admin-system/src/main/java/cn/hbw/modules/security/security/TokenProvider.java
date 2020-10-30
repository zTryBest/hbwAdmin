package cn.hbw.modules.security.security;

import cn.hbw.common.util.RedisUtils;
import cn.hbw.modules.security.config.bean.SecurityProperties;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName：cn.hbw.modules.security.security.TokenProvider
 * Description：token 令牌产生
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/21 9:12
 **/
@Component
@Slf4j
public class TokenProvider {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private static final String AUTHOR_KEY = "auth";
    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;

    public TokenProvider(SecurityProperties securityProperties, RedisUtils redisUtils) {
        this.properties = securityProperties;
        this.redisUtils = redisUtils;
    }

    /**
     * 初始化之前, 给 jwtParser, jwtBuilder 赋值
     */
    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        jwtBuilder = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS512);
    }

    /**
     * 创建 token 设置永不过期
     */
   public String createToken(Authentication authentication){
       String authorities = authentication.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.joining(","));

       return jwtBuilder
               .setId(IdUtil.simpleUUID())
               .claim(AUTHOR_KEY,authorities)
               .setSubject(authentication.getName())
               .compact();
   }

    /**
     * 从 token 获取鉴权信息
     */
    public Authentication getAuthentication(String token){
        Claims claims  =  getClaims(token);
        Object authorStr = claims.get(AUTHOR_KEY);
        Collection<? extends GrantedAuthority> authorities =
                ObjectUtil.isNotEmpty(authorStr) ?
                        Arrays.stream(authorStr.toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()) : Collections.emptyList();
        User principal = new User(claims.getSubject(), "******", authorities);
        return new UsernamePasswordAuthenticationToken(principal,token,authorities);
    }

    private Claims getClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 检查 token 续期
     * @param token
     */
    public void checkRenewal(String token){
        long time = redisUtils.getExpire(properties.getOnlineKey() + token) ;
        log.info("获取到的过期时间是{}s",time);
        // 如果在续期检查范围内，则续期
        if (time <= properties.getDetect()){
            long renew =  properties.getDetect();
            redisUtils.expire(properties.getOnlineKey()+token,renew, TimeUnit.SECONDS);
        }
    }

    public String getToken(HttpServletRequest request){
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }


}
