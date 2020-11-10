package cn.hbw.modules.security.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName：cn.hbw.modules.security.service.dto.AuthUserDto
 * Description： 用户登录类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/4 13:15
 **/
@Data
public class AuthUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

}
