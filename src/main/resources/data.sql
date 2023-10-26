--INSERT INTO PERSON
--    (ID, NAME, LOCATION, BIRTH_DATE)
--VALUES(10001, 'Peter', 'New York', CURRENT_TIMESTAMP);

insert into course(id, name, created_date, last_updated_date)
values (10001, 'JPA', LOCALTIMESTAMP, LOCALTIMESTAMP);

insert into course(id, name, created_date, last_updated_date)
values (10002, 'Spring', LOCALTIMESTAMP, LOCALTIMESTAMP);

insert into course(id, name, created_date, last_updated_date)
values (10003, 'Spring Boot', LOCALTIMESTAMP, LOCALTIMESTAMP);


insert into passport(id, number)
values(40001, 'E123');

insert into passport(id, number)
values(40002, 'X456');

insert into passport(id, number)
values(40003, 'Z789');



insert into student(id, name, passport_id)
values(20001, 'Niranjan', 40001);

insert into student(id, name, passport_id)
values(20002, 'Ranga', 40002);

insert into student(id, name, passport_id)
values(20003, 'Adam', 40003);



insert into review(id, rating, description)
values(50001, '5', 'Great Course');

insert into review(id, rating, description)
values(50002, '3', 'Fine');

insert into review(id, rating, description)
values(50003, '4', 'Awesome Course');