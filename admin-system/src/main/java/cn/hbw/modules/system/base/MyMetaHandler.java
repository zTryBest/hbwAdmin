package cn.hbw.modules.system.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassNameMyMetaHandler
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@Component
@Slf4j
public class MyMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"createBy",String.class,"陈冠希");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
        this.strictUpdateFill(metaObject,"updateBy",String.class,"陈冠希");
    }
}
