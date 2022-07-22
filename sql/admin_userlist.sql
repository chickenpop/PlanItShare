
-- admin - userlist

-- 유저정보 select
select * from tblUser order by regdate;
select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate from tblUser;

select * from (select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate, rownum as rnum from tblUser u) where rnum between 1 and 10;

-- 페이징용도 유저 insert

insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('aavid', '이기수', '01012365679', '1111', default, '22/07/16', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Devid', '이명수', '01012375679', '1111', default, '22/07/16', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('David', '이영수', '01012385679', '1111', default, '22/07/17', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Robert', '김영수', '01012345679', '1111', default, '22/07/17', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Michael', '김성호', '01022345678', '1111', default, '22/07/18', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Joseph', '이성민', '01032345678', '1111', default, '22/07/18', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Charles', '이지훈', '01042345678', '1111', default, '22/07/19', 'n', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Thomas', '박준서', '01052345678', '1111',  default, '22/07/19', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Edward', '박건우', '01062345678', '1111', default, '22/07/21', 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Andrew', '유진우', '01072345678', '1111', default, '22/07/21', 'n', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Brian', '이광수', '01082345678', '1111', default, '22/07/22','y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Kevin', '유재석', '01092345678', '1111',  default, '22/07/22','y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('cavid', '이박수', '01012355679', '1111', default, '22/07/16', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Joshua', '이미영', '01010345678', '1111',  default, '22/07/17', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Jennifer', '김예지', '01053345678', '1111',  default, '22/07/18', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Nancy', '김수민', '01064345678', '1111',  default, '22/07/19', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Carol', '이윤서', '01075345678', '1111',  default, '22/07/20', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Ashley', '박하은', '01086345678', '1111',  default, '22/07/20', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Melissa', '장민서', '01097345678', '1111',  default,'22/07/21', 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Laura', '송수빈', '01018345678', '1111', default, '22/07/21','y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('Donna', '유현서', '01018345678', '1111',  default, '22/07/22', 'y', 'f');
commit;
rollback;

-- 회원 탈퇴, active 비활성화
delete tblUser where id = 'Jennifer';


-- 테스트용 회원 활성화
update tblUser set active = 'y' where id = 'test10';

rollback;

-- 회원 총인원
select count(id) as cnt from tblUser;




