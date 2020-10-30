package cn.hbw.modules.system.service;

import cn.hbw.modules.system.service.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;

/**
 * InterfaceName：cn.hbw.modules.system.service.UserService
 * Description：user 业务类接口
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:34
 **/
public interface UserService {
    /**
     * 根据 userId 获取包装类
     * @param id
     * @return
     */
    UserDto selectUserDtoById(Long id);

    /**
     * 根据 username 获取包装类
     * @param username
     * @return
     */
    UserDto selectUserDtoByUsername(String username);
}
