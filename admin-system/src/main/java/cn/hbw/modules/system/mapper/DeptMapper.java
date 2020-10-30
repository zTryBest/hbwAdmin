package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * InterfaceName：cn.hbw.modules.system.mapper.DeptMapper
 * Description：部门数据类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 20:09
 **/
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
}
