drop table if exists resc;
drop table if exists role;
drop table if exists user;
drop table if exists resc_role;
drop table if exists user_role;

-- 资源
create table resc(
    id bigint,
    name varchar(50),
    res_type varchar(50),
    res_string varchar(200),
    priority integer,
    descn varchar(200)
);
alter table resc add constraint pk_resc primary key(id);

-- 角色
create table role(
    id bigint,
    name varchar(50),
    descn varchar(200)
);
alter table role add constraint pk_role primary key(id);

-- 用户
create table user(
    id bigint,
    username varchar(50),
    password varchar(50),
    status integer,
    descn varchar(200)
);
alter table user add constraint pk_user primary key(id);

-- 资源角色连接表
create table resc_role(
    resc_id bigint,
    role_id bigint
);
alter table resc_role add constraint pk_resc_role primary key(resc_id, role_id);

-- 用户角色连接表
create table user_role(
    user_id bigint,
    role_id bigint
);
alter table user_role add constraint pk_user_role primary key(user_id, role_id);
