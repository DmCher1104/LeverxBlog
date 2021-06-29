create TABLE blog.tag
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    PRIMARY KEY (id)
);

create TABLE blog.post
(
    id         int NOT NULL AUTO_INCREMENT,
    name       varchar(255), NOT NULL,
    text       Text, NOT NULL,
    status  varchar(255), NOT NULL,
    author_id  int(15), NOT NULL,
    created_at Date, NOT NULL,
    updated_at Date, NOT NULL,
    FOREIGN KEY (author_id) REFERENCES blog.users (id),
    PRIMARY KEY (id)
);

create TABLE blog.users
(
    id         int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    lastName  varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    created_at Date,
    enabled tinyint(1) NOT NULL,
    activateCode varchar(255)
    PRIMARY KEY (id)
);

create TABLE blog.comment
(
    id         int NOT NULL AUTO_INCREMENT,
    message    TEXT,
    post_id    int(15),
    author_id  int(15),
    created_at Date,
    FOREIGN KEY (author_id) REFERENCES blog.users (id),
    FOREIGN KEY (post_id) REFERENCES blog.post (id),
    PRIMARY KEY (id)
);

create TABLE blog.tag_x_post
(
    post_id int NOT NULL,
    tag_id  int NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    FOREIGN KEY (tag_id) REFERENCES blog.tag (id),
    FOREIGN KEY (post_id) REFERENCES blog.post (id)
);

create TABLE blog.role (
     id int NOT NULL AUTO_INCREMENT,
     name varchar(100),
     PRIMARY KEY (id)
);
