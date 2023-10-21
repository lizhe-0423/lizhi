create table lizhi.bi_api
(
    bi_id       varchar(255)               not null comment '主键'
        primary key,
    user_id     bigint                     not null comment '用户id',
    bi_name     varchar(25)                not null comment '业务名称',
    bi_type     varchar(15)                not null comment '业务类型',
    bi_style    varchar(15)                null comment '风格主题',
    bi_status   varchar(10)                null comment '状态',
    bi_content  varchar(255)               null comment '内容',
    create_time datetime   default (now()) not null comment '创建时间',
    update_time datetime                   null comment '修改时间',
    delete_time datetime                   null comment '删除时间',
    is_delete   tinyint(1) default 0       not null comment '逻辑删除 0 未删除 1 删除',
    constraint bi_api_users_id_fk
        foreign key (user_id) references lizhi.users (id)
)
    comment '基于BI的API功能表';

create table lizhi.bi_chart
(
    chart_id     bigint auto_increment
        primary key,
    user_id      bigint                             null comment '用户id',
    chart_goal   varchar(255)                       not null comment '目标',
    chart_name   varchar(25)                        not null comment '名称',
    chart_text   text                               not null comment '内容',
    chart_type   varchar(20)                        null comment '图表类型',
    chart_status varchar(10)                        null comment '状态',
    chart_gen    text                               null comment '代码',
    chart_result text                               null comment '分析结论',
    create_time  datetime default CURRENT_TIMESTAMP null invisible comment '创建时间',
    update_time  datetime                           null comment '更新时间',
    delete_time  datetime                           null comment '删除时间',
    constraint bi_chart_users_id_fk
        foreign key (user_id) references lizhi.users (id)
)
    comment 'BI图表表';

create table lizhi.leave_message
(
    id      int          not null comment '主键'
        primary key,
    user_id bigint       null comment '用户id',
    message varchar(255) null comment '消息内容',
    constraint leave_message_users_id_fk
        foreign key (user_id) references lizhi.users (id)
)
    comment '留言板';

create table lizhi.users
(
    id            bigint auto_increment
        primary key,
    user_name     varchar(255) default '荔枝'            not null comment '用户名',
    user_account  varchar(100)                           not null comment '用户账户',
    user_password varchar(255)                           not null comment '用户密码',
    user_photo    varchar(255) default '默认头像'        not null comment '用户头像',
    user_level    varchar(20)  default 'Normal'          not null comment '用户等级',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime                               null comment '修改时间',
    delete_time   datetime                               null comment '删除时间',
    is_delete     tinyint(1)   default 0                 not null comment '逻辑删除'
);

