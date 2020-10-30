package cn.hbw.modules.system.service.impl;

import cn.hbw.modules.system.mapper.JobMapper;
import cn.hbw.modules.system.service.JobService;
import cn.hbw.modules.system.service.dto.JobSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName：cn.hbw.modules.system.service.impl.JobServiceImpl
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 10:41
 **/
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobMapper jobMapper;
    @Override
    public List<JobSimpleDto> selectSimpleDtoByUserId(Long id) {
        return jobMapper.selectSimpleDtoByUserId(id);
    }
}
