package cn.hbw.modules.system.service.impl;

import cn.hbw.common.util.enums.DataScopeEnum;
import cn.hbw.modules.system.service.DataService;
import cn.hbw.modules.system.service.DeptService;
import cn.hbw.modules.system.service.RoleService;
import cn.hbw.modules.system.service.dto.RoleSimpleDto;
import cn.hbw.modules.system.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName：cn.hbw.modules.system.service.impl.DataServiceImpl
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 15:07
 **/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class DataServiceImpl implements DataService {
    private final DeptService deptService;
    private final RoleService roleService;

    /**
     * 用户角色改变时，要清理缓存
     * @param userDto
     * @return
     */
    @Override
    public List<Long> getDeptIds(UserDto userDto) {
        // 存储部门 id
        Set<Long> deptIds = new HashSet<>();
        // 查询用户角色
        List<RoleSimpleDto> roleSet = roleService.findSimpleDtoByUseId(userDto.getUserId());
        // 根据角色查询数据权限，获取部门 id
        for (RoleSimpleDto role : roleSet) {
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            switch (Objects.requireNonNull(dataScopeEnum)){
                case THIS_LEVEL:
                    deptIds.add(userDto.getDept().getId());
                    break;
                case CUSTOMIZE:
                    deptIds.add(getCustomize(deptIds, role));
                    break;
                default:
                    return new ArrayList<>(deptIds);
            }
        }
        return new ArrayList<>(deptIds);
    }

    private Long getCustomize(Set<Long> deptIds, RoleSimpleDto role) {
        return null;
    }
}
