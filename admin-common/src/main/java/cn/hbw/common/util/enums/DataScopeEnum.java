package cn.hbw.common.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName：cn.hbw.common.util.enums.DataScopeEnum
 * Description：数据权限枚举
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/10/29 15:19
 **/
@Getter
@AllArgsConstructor
public enum  DataScopeEnum {
    /** 全部数据权限 **/
    ALL("全部","全部数据权限"),

    /* 自己部门的数据权限 */
    THIS_LEVEL("本级", "自己部门的数据权限"),

    /* 自定义的数据权限 */
    CUSTOMIZE("自定义", "自定义的数据权限");

    private final String value;
    private final String description;

    public static DataScopeEnum find(String val){
        for (DataScopeEnum dataScopeEnum : DataScopeEnum.values()) {
            if (val.equals(dataScopeEnum.getValue())){
                return dataScopeEnum;
            }
        }
        return null;
    }

}
