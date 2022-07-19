
-- admin - userlist

-- 유저정보 select
select * from tblUser;
select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate from tblUser;

select * from (select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate, rownum as rnum from tblUser u) where rnum between 1 and 10;

-- 페이징용도 유저 insert

insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test1', '테스트1', '01012345679', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test2', '테스트2', '01022345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test3', '테스트3', '01032345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test4', '테스트4', '01042345678', '1111', default, default, 'n', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test5', '테스트5', '01052345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test6', '테스트6', '01062345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test7', '테스트7', '01072345678', '1111', default, default, 'n', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test8', '테스트8', '01082345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test9', '테스트9', '01092345678', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test10', '테스트10', '01010345678', '1111', default, default, 'y', 'm');

commit;


-- 회원 탈퇴, active 비활성화
update tblUser set active = 'n' where id = 'test9';

-- 테스트용 회원 활성화
update tblUser set active = 'y' where id = 'test10';

rollback;

-- 회원 총인원
select count(id) as cnt from tblUser;


