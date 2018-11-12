set search_path to newplatform;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','trung anh',1);
insert into users(username,password,fullname,status)
values('trung anh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','anh trung',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);

INSERT INTO category(code,name)
values('the-thao','Thể thao');
INSERT INTO category(code,name)
values('the-gioi','Thế giới');
INSERT INTO category(code,name)
values('chinh-tri','Chính trị');
INSERT INTO category(code,name)
values('thoi-su','Thời sự');
INSERT INTO category(code,name)
values('goc-nhin','Góc nhìn');

INSERT INTO new(title, category_id) values('MU & 14 năm đế chế Glazer: "Vua châu Phi" đến tỷ phú Ả Rập 850 tỷ bảng',1);
INSERT INTO new(title, category_id) values('MU & 14 năm đế chế Glazer: "Vua châu Phi" đến tỷ phú Ả Rập 850 tỷ bảng',1);
INSERT INTO new(title, category_id) values('MU & 14 năm đế chế Glazer: "Vua châu Phi" đến tỷ phú Ả Rập 850 tỷ bảng',1);

select * from users;
select * from role;
select * from new;