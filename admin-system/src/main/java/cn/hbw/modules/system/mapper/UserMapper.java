package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.User;
import cn.hbw.modules.system.service.dto.UserDto;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * InterfaceName：cn.hbw.modules.system.mapper.UserMapper
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:36
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义参数获取用户包装类
     * @param wrapper
     * @return
     */
    @Results(value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "jobs",many = @Many(select = "cn.hbw.modules.system.mapper.JobMapper.selectSimpleDtoByUserId")),
            @Result(column = "user_id",property = "roles",many = @Many(select = "cn.hbw.modules.system.mapper.RoleMapper.selectSimpleDtoByUserId"))
    })
    @Select("select * from hbw_user ${ew.customSqlSegment}")
    UserDto selectByWrapper(@Param("ew") Wrapper wrapper);

}
