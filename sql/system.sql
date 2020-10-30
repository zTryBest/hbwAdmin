CREATE TABLE `hbw_user` (
                            `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
                            `emp_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '员工工号，使用此列进行登录',
                            `name` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '姓名',
                            `password` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '{noop}1234abcd' COMMENT '密码',
                            `salt` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '盐',
                            `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
                            `picture` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
                            `sex` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT 'M:男 F: 女',
                            `phone_number` varchar(13) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
                            `education_level` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '文化程度',
                            `political_landscape` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '政治面貌',
                            `id_card` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
                            `nation` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '民族',
                            `locked` bit(1) DEFAULT NULL COMMENT '是否锁 0:正常 1: 已锁',
                            `status` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '状态 0: 正常 1: 借调 2: 离职',
                            `del_flag` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '是否删除 Y : 已删除 N : 未删除',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `create_by` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `update_by` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

create table if not exists hbw_job
(
    id int(10) auto_increment comment '主键Id'
        primary key,
    name varchar(20) null comment '职务名',
    job_sort int(5) null comment '排序',
    enabled bit null comment '是否启用',
    create_time datetime null comment '创建时间',
    create_by varchar(255) null comment '创建者',
    update_time datetime null comment '更新时间',
    update_by datetime null comment '更新者'
);

