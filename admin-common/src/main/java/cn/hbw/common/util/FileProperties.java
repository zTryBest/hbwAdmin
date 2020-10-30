package cn.hbw.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName：cn.hbw.common.util.FileProperties
 * Description：文件属性
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/19 13:56
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private HbwPath mac;

    private HbwPath linux;

    private HbwPath windows;

    public HbwPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(HbwConstant.WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(HbwConstant.MAC)){
            return mac;
        }
        return linux;
    }

    @Data
    public static class HbwPath{

        private String path;

        private String avatar;
    }
}
