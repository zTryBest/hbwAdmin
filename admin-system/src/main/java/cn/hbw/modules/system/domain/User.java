package cn.hbw.modules.system.domain;

import cn.hbw.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassNameUser
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@Data
@TableName("hbw_user")
public class User extends BaseEntity implements Serializable {

    private Long userId;

    private String empNumber;

    private String name;

    private String password;

    private String salt;

    private Long deptId;

    private String picture;

    private String sex;

    private String phoneNumber;

    private String educationLevel;

    private String politicalLandscape;

    private String idCard;

    private String nation;

    private Boolean locked;

    private String status;

    private String delFlag;
}

