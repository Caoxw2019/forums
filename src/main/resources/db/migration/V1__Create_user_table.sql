create table user
(
    id int auto_increment primary key not null,
    accout_id varchar(100),
    name varchar(50),
    token varchar(36),
    gmt_create bigint,
    gmt_modified bigint
);