-- sharding.`user` definition

CREATE TABLE `user` (
                        `uid` bigint NOT NULL,
                        `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `order0` (
                        `oid` bigint NOT NULL,
                        `buyer_id` bigint NOT NULL,
                        `seller_id` bigint NOT NULL,
                        PRIMARY KEY (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `order1` (
                          `oid` bigint NOT NULL,
                          `buyer_id` bigint NOT NULL,
                          `seller_id` bigint NOT NULL,
                          PRIMARY KEY (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;