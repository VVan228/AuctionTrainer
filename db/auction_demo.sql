USE DATABASE auction_demo;

DROP TABLE IF EXISTS `connected_users`;
CREATE TABLE `connected_users` (
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `connected_users_joined_users`;
CREATE TABLE `connected_users_joined_users` (
  `connected_users_room_id` bigint NOT NULL,
  `joined_users_id` bigint NOT NULL,
  KEY `FK6nlmo5169fpgvgtilq4hxbhem` (`joined_users_id`),
  KEY `FKssdn140kktq7qk06wb44upd61` (`connected_users_room_id`),
  CONSTRAINT `FK6nlmo5169fpgvgtilq4hxbhem` FOREIGN KEY (`joined_users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKssdn140kktq7qk06wb44upd61` FOREIGN KEY (`connected_users_room_id`) REFERENCES `connected_users` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval`;
CREATE TABLE `interval` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autoend` bit(1) DEFAULT NULL,
  `autostart` bit(1) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `entity_uid` binary(16) DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `type` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=479 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_intervals`;
CREATE TABLE `interval_intervals` (
  `interval_id` bigint NOT NULL,
  `intervals_id` bigint NOT NULL,
  UNIQUE KEY `UK_k4jffxgt7jlerkfmqffa2lq3i` (`intervals_id`),
  KEY `FKkt3fo1xh8a6ldpcme17526246` (`interval_id`),
  CONSTRAINT `FKkt3fo1xh8a6ldpcme17526246` FOREIGN KEY (`interval_id`) REFERENCES `interval` (`id`),
  CONSTRAINT `FKnybcc75ypi2bdjasiqlk6oq3o` FOREIGN KEY (`intervals_id`) REFERENCES `interval` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_point`;
CREATE TABLE `interval_point` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `at_least_one_auto_end` bit(1) DEFAULT NULL,
  `at_least_one_auto_start` bit(1) DEFAULT NULL,
  `at_least_one_manual_end` bit(1) DEFAULT NULL,
  `at_least_one_manual_start` bit(1) DEFAULT NULL,
  `queue_id` bigint DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_point_interval_end_ids`;
CREATE TABLE `interval_point_interval_end_ids` (
  `interval_point_id` bigint NOT NULL,
  `autoend` bit(1) DEFAULT NULL,
  `interval_id` bigint DEFAULT NULL,
  KEY `FKibx29xqm1bhsrx6edhy6su1lx` (`interval_point_id`),
  CONSTRAINT `FKibx29xqm1bhsrx6edhy6su1lx` FOREIGN KEY (`interval_point_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_point_interval_start_ids`;
CREATE TABLE `interval_point_interval_start_ids` (
  `interval_point_id` bigint NOT NULL,
  `autostart` bit(1) DEFAULT NULL,
  `interval_id` bigint DEFAULT NULL,
  KEY `FKtj1xg8pkc53acb44et7r94di6` (`interval_point_id`),
  CONSTRAINT `FKtj1xg8pkc53acb44et7r94di6` FOREIGN KEY (`interval_point_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_queue`;
CREATE TABLE `interval_queue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_index` bigint DEFAULT NULL,
  `room_uid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `interval_queue_interval_points`;
CREATE TABLE `interval_queue_interval_points` (
  `interval_queue_id` bigint NOT NULL,
  `interval_points_id` bigint NOT NULL,
  UNIQUE KEY `UK_5byuo89gk3mvg9va01u5ug65e` (`interval_points_id`),
  KEY `FKc1b9060gvnhgvrik9x7b9qhrt` (`interval_queue_id`),
  CONSTRAINT `FKc1b9060gvnhgvrik9x7b9qhrt` FOREIGN KEY (`interval_queue_id`) REFERENCES `interval_queue` (`id`),
  CONSTRAINT `FKcdni8ufaonam3oc4leix6lm02` FOREIGN KEY (`interval_points_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `lot`;
CREATE TABLE `lot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` binary(16) DEFAULT NULL,
  `end_on_all_answered` bit(1) DEFAULT NULL,
  `limit_sum` int DEFAULT NULL,
  `min_bet_step` int DEFAULT NULL,
  `start_sum` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sum` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnhk8321kn0ly9dscl849c30wi` (`user_id`),
  CONSTRAINT `FKnhk8321kn0ly9dscl849c30wi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `lot_bets`;
CREATE TABLE `lot_bets` (
  `lot_id` bigint NOT NULL,
  `sum` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  KEY `FKjxjwig9pg6pegbv3y9sdjd0b` (`user_id`),
  KEY `FKkkm4r01h33ipmnpukcju51eg0` (`lot_id`),
  CONSTRAINT `FKjxjwig9pg6pegbv3y9sdjd0b` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkkm4r01h33ipmnpukcju51eg0` FOREIGN KEY (`lot_id`) REFERENCES `lot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` binary(16) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `room_type` smallint DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtl14kvxhp8bam5wu4f1oqn3gs` (`creator_id`),
  CONSTRAINT `FKtl14kvxhp8bam5wu4f1oqn3gs` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `room_intervals`;
CREATE TABLE `room_intervals` (
  `room_id` bigint NOT NULL,
  `intervals_id` bigint NOT NULL,
  UNIQUE KEY `UK_6m9c9g02yo41f9m3mn1s1q862` (`intervals_id`),
  KEY `FK3l929apwwmprcbr1vf8l5ritp` (`room_id`),
  CONSTRAINT `FK3l929apwwmprcbr1vf8l5ritp` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FKstnuye035lrx5o1hlg45tp87k` FOREIGN KEY (`intervals_id`) REFERENCES `interval` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `round`;
CREATE TABLE `round` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` binary(16) DEFAULT NULL,
  `ascending` bit(1) DEFAULT NULL,
  `room_uid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approves_amount` int DEFAULT NULL,
  `creation_time` datetime(6) DEFAULT NULL,
  `end_on_all_answered` bit(1) DEFAULT NULL,
  `limit_sum` int DEFAULT NULL,
  `min_bet_step` int DEFAULT NULL,
  `start_sum` int DEFAULT NULL,
  `lot_duration` bigint DEFAULT NULL,
  `lot_pause_duration` bigint DEFAULT NULL,
  `round_pause_duration` bigint DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `is_private` bit(1) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `manual_mode` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdxhgepg2pwv3kvccbm8w5pilm` (`creator_id`),
  CONSTRAINT `FKdxhgepg2pwv3kvccbm8w5pilm` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_approval`;
CREATE TABLE `template_approval` (
  `template_id` bigint NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_approval_approvals`;
CREATE TABLE `template_approval_approvals` (
  `template_approval_template_id` bigint NOT NULL,
  `approvals` bit(1) DEFAULT NULL,
  `approvals_key` bigint NOT NULL,
  PRIMARY KEY (`template_approval_template_id`,`approvals_key`),
  CONSTRAINT `FK25yd891tg9gpukmntdgrs1cm2` FOREIGN KEY (`template_approval_template_id`) REFERENCES `template_approval` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_approval_user_ids`;
CREATE TABLE `template_approval_user_ids` (
  `template_approval_template_id` bigint NOT NULL,
  `user_ids` bigint DEFAULT NULL,
  KEY `FKennws7hae0j09y3dqeg2pa6np` (`template_approval_template_id`),
  CONSTRAINT `FKennws7hae0j09y3dqeg2pa6np` FOREIGN KEY (`template_approval_template_id`) REFERENCES `template_approval` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_lot_descriptions`;
CREATE TABLE `template_lot_descriptions` (
  `template_id` bigint NOT NULL,
  `lot_descriptions` varchar(255) DEFAULT NULL,
  KEY `FK651n2yw07uwmk8kc1x9nys255` (`template_id`),
  CONSTRAINT `FK651n2yw07uwmk8kc1x9nys255` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_lot_names`;
CREATE TABLE `template_lot_names` (
  `template_id` bigint NOT NULL,
  `lot_names` varchar(255) DEFAULT NULL,
  KEY `FKgc9bnpayigfahyepfistnr8py` (`template_id`),
  CONSTRAINT `FKgc9bnpayigfahyepfistnr8py` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_round_types`;
CREATE TABLE `template_round_types` (
  `template_id` bigint NOT NULL,
  `round_types` bit(1) DEFAULT NULL,
  KEY `FK96a73ui6ib9t1i4p5tii9mdlq` (`template_id`),
  CONSTRAINT `FK96a73ui6ib9t1i4p5tii9mdlq` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `template_rounds`;
CREATE TABLE `template_rounds` (
  `template_id` bigint NOT NULL,
  `end_on_all_answered` bit(1) DEFAULT NULL,
  `limit_sum` int DEFAULT NULL,
  `min_bet_step` int DEFAULT NULL,
  `start_sum` int DEFAULT NULL,
  `lot_duration` bigint DEFAULT NULL,
  `lot_pause_duration` bigint DEFAULT NULL,
  `round_pause_duration` bigint DEFAULT NULL,
  `ascending` bit(1) DEFAULT NULL,
  KEY `FKjqye7vx9j3fjc7rasmcqdre9w` (`template_id`),
  CONSTRAINT `FKjqye7vx9j3fjc7rasmcqdre9w` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_refresh_token_hash` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` smallint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
