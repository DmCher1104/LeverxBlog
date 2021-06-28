create TABLE blog.tag
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(15),
    PRIMARY KEY (id)
);

create TABLE blog.post
(
    id         int NOT NULL AUTO_INCREMENT,
    name       varchar(15),
    text       Text,
    status_id  int(15),
    author_id  int(15),
    created_at Date,
    updated_at Date,
    FOREIGN KEY (author_id) REFERENCES blog.user (id),
    FOREIGN KEY (status_id) REFERENCES blog.status (id),
    PRIMARY KEY (id)
);

create TABLE blog.user
(
    id         int NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    created_at Date,
    count_post int(10),
    PRIMARY KEY (id)
);

create TABLE blog.comment
(
    id         int NOT NULL AUTO_INCREMENT,
    message    TEXT,
    post_id    int(15),
    author_id  int(15),
    email      varchar(15),
    created_at Date,
    FOREIGN KEY (author_id) REFERENCES blog.user (id),
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

create TABLE blog.user_x_role
(
    role_id int NOT NULL,
    user_id  int NOT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (role_id) REFERENCES blog.role (id),
    FOREIGN KEY (user_id) REFERENCES blog.user (id)
);