package cn.hbw.modules.system.service;

import cn.hbw.modules.system.service.dto.JobSimpleDto;

import java.util.List;

/**
 * InterfaceName：cn.hbw.modules.system.service.JobService
 * Description：job 业务类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:40
 **/
public interface JobService {
    /**
     * 通过 id 查询职务集合
     * @param id
     * @return
     */
    List<JobSimpleDto> selectSimpleDtoByUserId(Long id);
}
