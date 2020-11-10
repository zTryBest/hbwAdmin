package cn.hbw.modules.system.service.impl;

import cn.hbw.common.exceptions.EntityNotFoundException;
import cn.hbw.modules.system.domain.User;
import cn.hbw.modules.system.mapper.UserMapper;
import cn.hbw.modules.system.service.JobService;
import cn.hbw.modules.system.service.UserService;
import cn.hbw.modules.system.service.dto.JobSimpleDto;
import cn.hbw.modules.system.service.dto.UserDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * ClassName：cn.hbw.modules.system.service.impl.UserServiceImpl
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:36
 **/
@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDto selectUserDtoById(Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        UserDto userDto = userMapper.selectByWrapper(wrapper);
        if (userDto==null){
            throw new EntityNotFoundException(User.class,"user_id",String.valueOf(id));
        }
        else {
            return userDto;
        }
    }

    @Override
    public UserDto selectUserDtoByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_number",username);
        UserDto userDto = userMapper.selectByWrapper(wrapper);
        if (userDto==null){
            throw new EntityNotFoundException(User.class,"emp_number",username);
        }
        else {
            return userDto;
        }
    }
}
