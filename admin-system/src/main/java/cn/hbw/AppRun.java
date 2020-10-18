package cn.hbw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassNameAppRun
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@SpringBootApplication
@MapperScan("cn.hbw.modules.system.mapper")
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }
}
