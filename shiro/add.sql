-- we don't know how to generate root <with-no-name> (class Root) :(
create table member
(
    mid int auto_increment comment '用户Id'
        primary key,
    username varchar(100) not null comment '用户名',
    password varchar(128) not null comment '密码',
    phone varchar(11) null comment '手机',
    sex tinyint(1) default 1 null comment '性别 1 表示男 0 表示女',
    email varchar(100) not null comment '邮箱',
    mark varchar(100) null comment '备注',
    last_login timestamp default CURRENT_TIMESTAMP not null comment '最后一次登录时间',
    login_ip varchar(30) null comment '登录ip',
    head varchar(100) default '/img/default.png' null comment '头像图片路径',
    reg_time timestamp default CURRENT_TIMESTAMP not null comment '注册时间',
    locked tinyint(1) default 0 null comment '账号是否被锁定 1 表示未锁定 0 表示锁定',
    constraint uk_u_email
        unique (email),
    constraint uk_u_name
        unique (username),
    constraint uk_u_phone
        unique (phone)
);

create table member_role_relation
(
    id int auto_increment
        primary key,
    member_id int null,
    role_id int null,
    create_date datetime default CURRENT_TIMESTAMP null
);

create table permission
(
    per_id int auto_increment
        primary key,
    per_name varchar(255) not null comment '权限名称',
    create_date datetime default CURRENT_TIMESTAMP not null comment '创建日期',
    per_desc varchar(255) null comment '权限说明',
    is_del int default 0 not null comment '是否删除 0 表示未删除  1 表示删除',
    constraint per_name
        unique (per_name)
);

create table role
(
    role_id int auto_increment
        primary key,
    role_name varchar(64) not null comment '角色名称',
    create_date datetime default CURRENT_TIMESTAMP not null comment '创建日期',
    role_desc varchar(255) null comment '角色说明',
    is_del int default 0 not null comment '是否删除 0 表示未删除  1 表示删除'
);

create table role_permission_relation
(
    id int auto_increment
        primary key,
    per_id int null,
    role_id int null
);



