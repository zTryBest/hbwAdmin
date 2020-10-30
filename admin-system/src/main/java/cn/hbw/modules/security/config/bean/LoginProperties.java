package cn.hbw.modules.security.config.bean;

import lombok.Data;

/**
 * ClassName：cn.hbw.modules.security.config.bean.LoginProperties
 * Description：登录属性
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/20 16:57
 **/
@Data
public class LoginProperties {
    /**
     * 账号是否单用户登录
     */
    private boolean singleLogin = false;


    /**
     * 用户登录信息缓存
     */
    private boolean cacheEnable;

    public boolean isSingleLogin() {
        return singleLogin;
    }

    public boolean isCacheEnable() {
        return cacheEnable;
    }
}

