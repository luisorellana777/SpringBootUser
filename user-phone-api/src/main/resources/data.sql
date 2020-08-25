insert into user (id, created, email, is_active, last_login, last_name, name, password, token) values (1, sysdate(), 'Jill@gmail.com', true, sysdate(), 'Adam', 'Jill', '123', null);

insert into phone (id, citycode, contrycode, number, user_id) values (1, 111, 222, '12345', 1);
insert into phone (id, citycode, contrycode, number, user_id) values (2, 444, 555, '12345', 1);
insert into phone (id, citycode, contrycode, number, user_id) values (3, 666, 777, '12345', 1);