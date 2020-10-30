package cn.hbw.modules.security.config;

import cn.hbw.modules.security.config.bean.LoginProperties;
import cn.hbw.modules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName：cn.hbw.modules.security.config.ConfigBeanConfiguration
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/19 15:18
 **/
@Configuration
public class ConfigBeanConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties(){
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties(){
        return new SecurityProperties();
    }
}
