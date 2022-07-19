
-- admin - chart

-- 남자 회원가입
select 
       count(*) as cnt,
       to_date(regdate, 'yy/mm/dd') as regdate
  from tblUser 
 where regdate >= to_date('22/07/14', 'yy/mm/dd') and regdate <= to_date('22/07/20', 'yy/mm/dd') and gender = 'm'
 group by to_date(regdate, 'yy/mm/dd')
 order by regdate;
 
-- 여자 회원가입
select 
       count(*) as cnt,
       to_date(regdate, 'yy/mm/dd') as regdate
  from tblUser 
 where regdate >= to_date('22/07/14', 'yy/mm/dd') and regdate <= to_date('22/07/20', 'yy/mm/dd') and gender = 'f'
 group by to_date(regdate, 'yy/mm/dd')
 order by regdate;
 
select * from tblUser;

-- 회원가입 차트 최근날짜
select
    to_date('22/07/14', 'yy-mm-dd') + (level-1) as regdate
from dual
    connect by level <= (to_date('22/07/20', 'yy-mm-dd') - to_date('22/07/14', 'yy-mm-dd') + 1);

-- 차트 테스트용 더미
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test11', '테스트11', '01062145678', '1111', default, to_date('22/07/19', 'yy/mm/dd'), 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test12', '테스트12', '01072245678', '1111', default, to_date('22/07/15', 'yy/mm/dd'), 'y','f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test13', '테스트13', '01082345678', '1111', default, to_date('22/07/15', 'yy/mm/dd'), 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test14', '테스트14', '01092445678', '1111', default, to_date('22/07/16', 'yy/mm/dd'), 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test15', '테스트15', '01010545678', '1111', default, to_date('22/07/17', 'yy/mm/dd'), 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test16', '테스트16', '01082645678', '1111', default, to_date('22/07/16', 'yy/mm/dd'), 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test17', '테스트17', '01082745678', '1111', default, to_date('22/07/18', 'yy/mm/dd'), 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test18', '테스트18', '01082845678', '1111', default, to_date('22/07/18', 'yy/mm/dd'), 'y', 'f');

commit;

-- 테스트 계정 컬럼을 위한 삭제
delete tblUser where id = 'test18';



