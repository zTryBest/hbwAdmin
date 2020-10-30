package cn.hbw.modules.security.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName：cn.hbw.modules.security.service.dto.OnlineUserDto
 * Description：扩展类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/27 15:19
 **/
@Data
@NoArgsConstructor
public class OnlineUserDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 岗位
     */
    private String dept;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private Date loginTime;
}
