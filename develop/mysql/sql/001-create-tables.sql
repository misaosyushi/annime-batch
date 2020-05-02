---- drop ----
DROP TABLE IF EXISTS `season`;
DROP TABLE IF EXISTS `annimes`;
DROP TABLE IF EXISTS `episodes`;
DROP TABLE IF EXISTS `casts`;

---- create ----
CREATE TABLE `season` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL,
  `season` varchar(5) NOT NULL,
  `season_text` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `annimes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `annict_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `media` varchar(10) NOT NULL,
  `season_id` int NOT NULL,
  `official_site_url` varchar(400) NOT NULL,
  `twitter_user_name` varchar(100),
  `image_url` varchar(400),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `episodes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `annime_id` int NOT NULL,
  `number_text` varchar(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `casts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `annime_id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `character` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- insert --
INSERT INTO `season` VALUES
(1, 2020, '春', '2020年春'),
(2, 2020, '夏', '2020年夏'),
(3, 2020, '秋', '2020年秋'),
(4, 2020, '冬', '2020年冬');

INSERT INTO `annimes` VALUES
(1, 1, 'テストアニメ', 'TV', 1, 'https://google.com', 'twitter name', 'https://google.com');

INSERT INTO `episodes` VALUES
(1, 1, '1話', 'エピソード1');

INSERT INTO `casts` VALUES
(1, 1, 'ほげ太郎', 'hogehoge');

