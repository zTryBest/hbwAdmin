package cn.hbw.common.config;

import cn.hbw.common.util.HbwConstant;
import cn.hbw.common.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName：cn.hbw.common.config.PermissionConfig
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/10 13:45
 **/
@Service("hbw")
public class PermissionConfig {
    public Boolean check(String ...permissions){
        // 获取当前用户的数据权限
        List<String> currentPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return currentPermissions.contains(HbwConstant.ADMIN) || Arrays.stream(permissions).anyMatch(currentPermissions::contains);
    }
}
