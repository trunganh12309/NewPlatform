set search_path to newplatform;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','trung anh',1);
insert into users(username,password,fullname,status)
values('trung anh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','anh trung',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);
