DROP TABLE IF EXISTS `blog_posts_tags`;
DROP TABLE IF EXISTS `blog_posts`;
DROP TABLE IF EXISTS `tags`;

CREATE TABLE `blog_posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `handle` varchar(150) NOT NULL,
  `description` varchar(255) NOT NULL,
  `img_ref` varchar(255) DEFAULT NULL,
  `markdown_content` text,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  UNIQUE KEY `blog_posts_title_UNIQUE` (`title`),
  UNIQUE KEY `blog_posts_handle_UNIQUE` (`handle`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE UNIQUE INDEX `blog_posts_handle` ON `blog_posts`(`handle`);

CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE UNIQUE INDEX `tags_name` ON `tags`(`name`);

CREATE TABLE `blog_posts_tags` (
	`blog_post_id` INT NOT NULL,
    `tag_id` INT NOT NULL,
    PRIMARY KEY (`blog_post_id`,`tag_id`),
	CONSTRAINT `blog_post_id_fk_1` FOREIGN KEY (`blog_post_id`) REFERENCES `blog_posts` (`id`),
	CONSTRAINT `tag_id_fk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;