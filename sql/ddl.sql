-- 创建库
create database if not exists sugoj;

-- 切换库
use sugoj;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';

-- 帖子表
create table if not exists post
(
    id            bigint auto_increment comment 'id' primary key,
    age           int comment '年龄',
    gender        tinyint  default 0                 not null comment '性别（0-男, 1-女）',
    education     varchar(512)                       null comment '学历',
    place         varchar(512)                       null comment '地点',
    job           varchar(512)                       null comment '职业',
    contact       varchar(512)                       null comment '联系方式',
    loveExp       varchar(512)                       null comment '感情经历',
    content       text                               null comment '内容（个人介绍）',
    photo         varchar(1024)                      null comment '照片地址',
    reviewStatus  int      default 0                 not null comment '状态（0-待审核, 1-通过, 2-拒绝）',
    reviewMessage varchar(512)                       null comment '审核信息',
    viewNum       int                                not null default 0 comment '浏览数',
    thumbNum      int                                not null default 0 comment '点赞数',
    userId        bigint                             not null comment '创建用户 id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除'
) comment '帖子';

-- 题目表
create table if not exists question
(
    id            bigint auto_increment comment 'id' primary key,
    title         varchar(512)                       null comment '标题',
    content       text                               null comment '内容',
    tags          varchar(1024)                      null comment '标签列表(json数组)',
    answer        text                               null comment '题目答案',
    submitNum     int          default 0  not null   comment '题目提交数',
    acceptNum     int          default 0  not null   comment '题目通过数',
    judgeCase     text                               null comment '判题用例json',
    judgeConfig     text                               null comment '判题配置json',
    userId        bigint                             not null comment '创建用户 id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除',
    index  idx_userId(userId)
    ) comment '题目';

    -- 题目提交表
    create table if not exists question_submit
    (
        id            bigint auto_increment comment 'id' primary key,
        userId        bigint                             not null comment '用户id',
        questionId    bigint                             not null comment '题目id',
        language      varchar(128)                       not null comment '编程语言',
        code          text                               not null comment '用户代码',
        judgeInfo     text                               null comment '判题信息json',
        status        int          default 0             not null   comment '判题状态(0-待判题 1-判题中 2-成功 3-失败)',
        createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
        updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
        isDelete      tinyint  default 0                 not null comment '是否删除',
        index  idx_userId(userId),
        index  idx_questionId(questionId)
        ) comment '题目提交';