package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.Dept;
import cn.hbw.modules.system.service.dto.DeptSimpleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    /**
     * 通过 id 查询
     *
     * @param deptId
     * @return
     */
    @Select("select dept_id as id ,name from hbw_dept where dept_id = #{deptId}")
    DeptSimpleDto selectSimpleDtoByDeptId(Long deptId);

}
