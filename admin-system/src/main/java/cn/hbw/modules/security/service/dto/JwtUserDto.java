package cn.hbw.modules.security.service.dto;


import cn.hbw.modules.system.service.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName：cn.hbw.modules.security.service.dto.JwtUserDto
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/27 15:53
 **/
@Slf4j
@Getter
@AllArgsConstructor
public class JwtUserDto implements UserDetails {

    private final UserDto user;

    private final List<Long> dataScopes;

    private final List<GrantedAuthority> authorities;

    public Set<String> getPermissions(){
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getEmpNumber();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
