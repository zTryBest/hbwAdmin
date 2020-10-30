package cn.hbw.modules.system.service;

import cn.hbw.modules.system.service.dto.UserDto;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.service.DataService
 * Description：数据权限服务类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 15:06
 **/
public interface DataService {
    /**
     * 获取数据权限
     * @param userDto
     * @return
     */
    List<Long> getDeptIds(UserDto userDto);
}
