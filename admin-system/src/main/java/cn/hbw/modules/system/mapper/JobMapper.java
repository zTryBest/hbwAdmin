package cn.hbw.modules.system.mapper;

import cn.hbw.modules.system.domain.Job;
import cn.hbw.modules.system.service.dto.JobSimpleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.mapper.JobMapper
 * Description：job mapper类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:38
 **/
@Mapper
public interface JobMapper extends BaseMapper<Job> {
    /**
     * 返回 简单包装类
     * @param id
     * @return
     */
    @Select("select job_id as id,name from hbw_job where job_id in" +
            " (select huj.job_id from hbw_users_jobs huj where huj.user_id = #{id} ) ")
    List<JobSimpleDto> selectSimpleDtoByUserId(Long id);
}
