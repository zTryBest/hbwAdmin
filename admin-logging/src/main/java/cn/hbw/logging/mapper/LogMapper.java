package cn.hbw.logging.mapper;

import cn.hbw.logging.domain.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName LogMapper
 * @Description
 * @Author zzj
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
