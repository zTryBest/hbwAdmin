package cn.hbw.modules.security.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName：cn.hbw.modules.security.config.bean.SecurityProperties
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/19 15:26
 **/
@Data
public class SecurityProperties {
    /**
     *  RequestHeader: Authorization
     */
    private String header;

    /**
     *  token前缀: Bearer 空格
     */
    private String tokenStartWith;

    /**
     *  使用 Base64 对令牌编码
     */
    private String base64Secret;

    /**
     *  token令牌有效时间 (单位: 秒)
     */
    private Long tokenValidityInSeconds;

    /**
     * 在线用户 key , 存放 redis
     */
    private String onlineKey;

    /**
     * 验证码 key
     */
    private String codeKey;

    /**
     *  token 续期检查
     */
    private Long detect;

    /**
     *  续期时间
     */
    private Long time;

    /**
     * token 前缀获取,注意空格
     * @return
     */
    public String getTokenStartWith(){
        return tokenStartWith+" ";
    }


}
