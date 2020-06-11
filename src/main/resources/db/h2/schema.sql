DROP TABLE IF EXISTS blog_posts_tags;
DROP TABLE IF EXISTS blog_posts;
DROP TABLE IF EXISTS tags;

CREATE SEQUENCE IF NOT EXISTS blog_posts_seq;

CREATE TABLE blog_posts
(
    id               int          NOT NULL DEFAULT NEXTVAL('blog_posts_seq'),
    title            varchar(100) NOT NULL,
    handle           varchar(150) NOT NULL,
    description      varchar(255) NOT NULL,
    img_ref          varchar(255)          DEFAULT NULL,
    markdown_content text,
    created_at       timestamp(0) NOT NULL,
    updated_at       timestamp(0)          DEFAULT NULL,
    status           varchar(30)  NOT NULL,
    CONSTRAINT blog_posts_title_UNIQUE UNIQUE (title),
    CONSTRAINT blog_posts_handle_UNIQUE UNIQUE (handle),
    PRIMARY KEY (id)
);

ALTER SEQUENCE blog_posts_seq RESTART WITH 1;

CREATE UNIQUE INDEX blog_posts_handle ON blog_posts (handle);

CREATE SEQUENCE IF NOT EXISTS tags_seq;

CREATE TABLE tags
(
    id   int         NOT NULL DEFAULT NEXTVAL('tags_seq'),
    name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT name_UNIQUE UNIQUE (name)
);

ALTER SEQUENCE tags_seq RESTART WITH 1;

CREATE UNIQUE INDEX tags_name ON tags (name);

CREATE TABLE blog_posts_tags
(
    blog_post_id INT NOT NULL,
    tag_id       INT NOT NULL,
    PRIMARY KEY (blog_post_id, tag_id),
    CONSTRAINT blog_post_id_fk_1 FOREIGN KEY (blog_post_id) REFERENCES blog_posts (id),
    CONSTRAINT tag_id_fk_2 FOREIGN KEY (tag_id) REFERENCES tags (id)
);