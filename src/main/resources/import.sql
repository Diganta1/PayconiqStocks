insert into stock values(10001,'sogeti');
insert into stock values(10002,'ABN AMRO');

insert into price values(11001,200.05,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11002,200.07,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11003,200.09,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11004,200.09,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10002);


INSERT INTO tbl_user (id, username, password, salary, age) VALUES (1, 'digi',   '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 3456, 33);