package cn.hbw;

import cn.hbw.common.util.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassNameAppRun
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@SpringBootApplication
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
