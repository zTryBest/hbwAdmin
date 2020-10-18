package cn.hbw.modules.system.service.impl;

import cn.hbw.modules.system.domain.User;
import cn.hbw.modules.system.mapper.UserMapper;
import cn.hbw.modules.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassNameUserServiceImpl
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userMapper.updateById(user);
    }


}
