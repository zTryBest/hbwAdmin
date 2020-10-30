package cn.hbw;

import cn.hbw.modules.system.service.RoleService;
import cn.hbw.modules.system.service.UserService;
import cn.hbw.modules.system.service.dto.JobSimpleDto;
import cn.hbw.modules.system.service.dto.RoleSimpleDto;
import cn.hbw.modules.system.service.dto.UserDto;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：cn.hbw.MapperTest
 * Description：mapper 单元测试
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/28 21:42
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapperTest {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 测试通过 用户id 获取用户包装类
     */
    @Test
    public void test02(){
        UserDto userDto = userService.selectUserDtoById(1L);
        log.info("userDto:{}",userDto);
    }

    /**
     * 测试 获取权限列表功能
     */
    @Test
    public void test03(){
        UserDto userDto = userService.selectUserDtoById(1L);
        List<GrantedAuthority> grantedAuthorities = roleService.mapToGrantedAuthorities(userDto);
        grantedAuthorities.forEach(item -> {
            log.info(item.getAuthority());
        });
    }

}
