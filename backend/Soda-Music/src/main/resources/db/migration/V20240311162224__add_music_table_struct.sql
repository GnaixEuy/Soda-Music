CREATE TABLE `soda-music`.`music`
(
    `id`                 varchar(64)  NOT NULL COMMENT '主键id',
    `created_time`       datetime     NULL,
    `updated_time`       datetime     NULL,
    `created_user`       varchar(64)  NULL,
    `updated_user`       varchar(64)  NULL,
    `file_id`            varchar(64)  NULL,
    `music_name`         varchar(64)  NULL COMMENT '音乐名',
    `music_desc`         varchar(255) NULL COMMENT '音乐描述',
    `artist_id`          varchar(64)  NULL COMMENT '作者',
    `music_release_time` datetime     NULL COMMENT '音乐发布时间',
    `music_album_id`     varchar(64)  NULL COMMENT '专辑',
    `music_play_amount`  bigint       NULL DEFAULT 0 COMMENT '播放量',
    `cover_file_id`      varchar(64)  NULL COMMENT '封面文件表',
    PRIMARY KEY (`id`)
);