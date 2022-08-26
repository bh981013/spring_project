create table member(
member_id bigint not null auto_increment,
member_name varchar(20),
password varchar(20),
primary key(member_id)
);

create table post(
post_id bigint not null auto_increment,
member_id bigint,
post_title varchar(50),
post_body varchar(200),
post_date datetime,
primary key(post_id),
foreign key (member_id) references member(member_id) on delete cascade
);



