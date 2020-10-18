package cn.hbw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName ConfigAdapter
 * @Description 跨域配置
 * @Author zzj
 * @Date 2020/10/18
 * @Version V1.0
 **/
@Configuration
@EnableWebMvc
public class ConfigAdapter implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowCredentials(true).allowedMethods("*");
    }
}
