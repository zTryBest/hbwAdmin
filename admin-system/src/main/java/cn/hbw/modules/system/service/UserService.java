package cn.hbw.modules.system.service;

import cn.hbw.modules.system.domain.User;

import java.util.List;

/**
 * @ClassNameUserService
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
public interface UserService {
    List<User> findAll();
    void addUser(User user);

    void deleteById(Long id);

    void update(User user);
}
