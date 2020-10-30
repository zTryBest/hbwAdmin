package cn.hbw.modules.system.service.dto;

import cn.hbw.common.base.BaseEntity;
import cn.hbw.modules.system.domain.Job;
import cn.hbw.modules.system.domain.User;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Many;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName：cn.hbw.modules.system.service.dto.UserDto
 * Description：
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/27 15:55
 **/
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {

    private List<JobSimpleDto> jobs;

    private List<RoleSimpleDto> roles;

    private DeptSimpleDto dept;

}
