package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceNameUserMapper
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from hbw_user")
    List<User> findAll();
}
