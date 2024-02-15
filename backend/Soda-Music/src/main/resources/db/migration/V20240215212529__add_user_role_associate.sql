CREATE TABLE `soda-music`.`user_role_associate`
(
    `user_id` varchar(64) NOT NULL,
    `role_id` varchar(64) NOT NULL
#     CONSTRAINT `c_user_id` FOREIGN KEY (`user_id`) REFERENCES `soda-music`.`user` (`id`),
#     CONSTRAINT `c_role_id` FOREIGN KEY (`role_id`) REFERENCES `soda-music`.`role` (`id`)
) COMMENT = '用户角色关系表';


INSERT INTO `user` (id, username, nickname, password, created_time, updated_time)
VALUES ('1', 'admin', 'GnaixEuy', '$2a$10$tLL4xDMkoWZN98GKxueh5uvUzT2jwjXTn7I5uDx95pWzC2PWar5bS',
        '2021-07-21 09:27:12.260000',
        '2021-07-21 09:27:12.260000');
INSERT INTO `user` (id, username, nickname, password, created_time, updated_time)
VALUES ('2', 'admin22', 'cathat-music', '$2a$10$tLL4xDMkoWZN98GKxueh5uvUzT2jwjXTn7I5uDx95pWzC2PWar5bS',
        '2021-07-21 09:27:12.260000',
        '2021-07-21 09:27:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `user_role_associate` (user_id, role_id)
VALUES ('1', '1');
INSERT INTO `user_role_associate` (user_id, role_id)
VALUES ('1', '2');
INSERT INTO `user_role_associate` (user_id, role_id)
VALUES ('2', '1');
INSERT INTO `user_role_associate` (user_id, role_id)
VALUES ('2', '2');