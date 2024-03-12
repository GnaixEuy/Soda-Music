CREATE TABLE user
(
    id              VARCHAR(64)          NOT NULL PRIMARY KEY COMMENT '用户ID',
    created_time    datetime             NULL,
    updated_time    datetime             NULL,
    created_user    VARCHAR(64)          NULL,
    updated_user    VARCHAR(64)          NULL,
    username        VARCHAR(64)          NULL,
    nickname        VARCHAR(128)         NULL,
    password        VARCHAR(64)          NULL,
    gender          VARCHAR(12)          NULL,
    `locked`        tinyint(1) DEFAULT 0 NOT NULL COMMENT '是否锁定，1-是，0-否',
    enabled         tinyint(1) DEFAULT 1 NOT NULL COMMENT '是否可用，1-是，0-否',
    last_login_ip   VARCHAR(64)          NULL,
    last_login_time datetime             NULL
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

CREATE TABLE role
(
    id           VARCHAR(64)  NOT NULL PRIMARY KEY COMMENT '角色ID',
    name         VARCHAR(128) NULL COMMENT '角色名称',
    title        VARCHAR(128) NULL COMMENT '角色标识',
    created_time datetime     NOT NULL COMMENT '创建时间',
    updated_time datetime     NOT NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '角色表';