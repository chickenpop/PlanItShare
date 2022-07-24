
-- 팀 쿼리
create or replace view vwLodging
as
select
    l.seq, 
    l.name, 
    l.address, 
    l.checkin, 
    l.checkout, 
    l.image, 
    l.cseq, 
    l.lcseq,
    lc.category, 
    c.name as city,
    (select count(*) from tblLikeLodging ll where ll.lseq = l.seq) as likecnt, 
    (select count(*) from tblLodgingReview lr where lr.lseq = l.seq) as reviewcnt,
    (select round(avg(star),1) from tblLodgingReview lr where lr.lseq = l.seq) as reviewavg
from tblLodging l inner join tblCity c on l.cseq = c.seq
        inner join tblLodgingCategory lc on l.lcseq = lc.seq where c.seq = 1 order by likecnt desc;
        
create or replace view vwSPlan
as
select 
    p.seq,
    p.cseq,
    p.regdate,
    p.readcount,
    p.startdate,
    p.enddate,
    p.title,
    p.content,
    p.author,
    c.name,
    c.image,
    (select count(*) from tblLikePlan lp where lp.pseq = p.seq) as likecnt,
    (select count(*) from tblComment c where c.pseq = p.seq) as reviewcnt
from tblPlan p inner join tblCity c on p.cseq = c.seq order by likecnt desc;


-- 


CREATE OR REPLACE VIEW vwGetmycomment
AS
    select rownum as rnum, seq, content, regdate, title, id 
    from(select rownum as rnum, c.seq as seq, c.content as content, c.regdate as regdate, p.title as title, u.id as id  
            from tblComment c inner join tblUser u on c.id = u.id  inner join tblPlan p on c.pseq = p.seq  order by seq desc)
;



CREATE OR REPLACE VIEW vwLodgingReview 
AS
    select rownum as rnum, seq, id, content, regdate, place, city from 
    (select rownum as rnum, lr.seq as seq, lr.id as id, lr.content as content, lr.regdate as regdate, l.name as place, c.name as city
        from tblLodging l
            inner join tblCity c on l.cseq = c.seq
                inner join tblLodgingReview lr on lr.lseq = l.seq order by seq desc)
;   



CREATE OR REPLACE VIEW vwTourReview 
AS
    select rownum as rnum, seq, id, content, regdate, place, city from
    (select rownum, tr.seq as seq, tr.id as id, tr.content as content, tr.regdate as regdate, t.placename as place, c.name as city
        from tblTour t
            inner join tblCity c on t.cseq = c.seq
                inner join tblTourReview tr on tr.tseq = t.seq order by seq desc)
;   

CREATE OR REPLACE VIEW vwFoodReview 
AS
   select rownum as rnum, seq, id, content, regdate, place, city from (select rownum, fr.seq as seq, fr.id as id, fr.content as content, fr.regdate as regdate, f.name as place, c.name as city from tblFood f
            inner join tblCity c on f.cseq = c.seq
                inner join tblFoodReview fr on fr.fseq = f.seq order by seq desc)
;


--
insert into tblRecommendCity values(seqRecommendCity.nextVal, 1);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 2);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 3);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 4);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 5);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 6);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 7);
insert into tblRecommendCity values(seqRecommendCity.nextVal, 8);

alter table tblCity add engname varchar(20);

update tblCity set engname = 'jeju' where seq = 1;
update tblCity set engname = 'seoul' where seq = 2;
update tblCity set engname = 'busan' where seq = 3;
update tblCity set engname = 'gyeongju' where seq = 4;
update tblCity set engname = 'jinju' where seq = 5;
update tblCity set engname = 'gangneung' where seq = 6;
update tblCity set engname = 'yeosu' where seq = 7;
update tblCity set engname = 'jeonju' where seq = 8;


select * from tblPlan;
select * from tblcity;
select * from tblUser;

-- 창현님 제외, 주요도시 만들기
insert into tblPlan (seq, cseq, regdate, readcount, startdate, enddate, title, content, author) values(seqPlan.nextVal, 1, sysdate, 5, '2022-07-23', '2022-07-25', '제주도 2박 3일 맛집투어', '제주도에 있는 맛집 다 먹고 간다!!', '창현님아이디');
insert into tblPlan values(seqPlan.nextVal, 3, sysdate, 2, '2022-07-24', '2022-07-25', '부산 완전정복하기', '2박 3일 동안 부산 완벽하게 정복하는 일정 계획했다!', 'chae');
insert into tblPlan values(seqPlan.nextVal, 4, sysdate, 4, '2022-07-25', '2022-07-27', '경주 유적지 탐방기', '경주 월성 역사 유적지구에서 첨성대 필수, 안압지, 불국사, 석굴암도 다녀오기', 'kim');
insert into tblPlan values(seqPlan.nextVal, 5, sysdate, 6, '2022-07-28', '2022-07-30', '휴가는 진주에서 힐링', '진주 유등축제는 밤에 가는 거로 예정~ 경남 진주에서 가장 오래되고 큰 시장이라 하루만에 보기 힘들니 나눠서 일정구성', 'baek');
insert into tblPlan values(seqPlan.nextVal, 6, sysdate, 8, '2022-07-30', '2022-07-31', '열심히 강릉에서 먹고 오기', '매년 다녀오는 강릉여행, 맛집 위주로 다녀오기', 'yoon');
insert into tblPlan values(seqPlan.nextVal, 7, sysdate, 10, '2022-07-30', '2022-07-31', '뚜벅이 여수계획', 'KTX 예매해서 여수 출발, 내려서 근처 수제버거 먹기', 'suea724');

insert into tblPlan values(seqPlan.nextVal, 3, sysdate, 12, '2022-07-28', '2022-07-31', '차없이 부산 여행!', '호텔하고 여행동선 확인해서 역 락커에 물건 넣기, 교통카드 잊지말고 챙기기', 'ckdgus1999');
insert into tblPlan values(seqPlan.nextVal, 4, sysdate, 11, '2022-07-24', '2022-07-29', '경주에서 가족여행', '가족, 친척들과 단체로 여행 일정이라 방이랑 화장실이 많은 풀빌라로 예약해야한다 후보지 중 투표잊지말기 여행계획은 숙소 근처로', 'ckdgus1999');
insert into tblPlan values(seqPlan.nextVal, 7, sysdate, 15, '2022-07-25', '2022-07-26', '친구들과 여수 계획', '오랜만에 친구들과 겹치는 시간이 모여서 가는 여행, 렌트 필수 숙소는 바다앞으로 예약했다', 'ckdgus1999');



-- 더미 팀원

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

-- 메종 글래드 제주 seq 3
select * from tblLodging;
select * from tblLodgingReview;
select * from tblLikeLodging;

-- 리뷰
insert into tblLodgingReview values(seqLodgingReview.nextVal, '청결해서 좋습니다', 4.0, default, 'Laura', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '서비스가 별로에요 별로 추천하지 않아요', 3.5, default, 'Ashley', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '숙소상태도 좋고 직원분들이 친철했어요', 5.0, default, 'Joseph', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '위치도 마음에 들고 숙소상태도 좋았습니다', 4.0, default, 'Brian', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '호텔 안에 편의점이 있어서 편리했습니다 추천드려요', 5.0, default, 'Jennifer', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '주차가 조금 문제이긴 했는데 다른 것들은 전부 만족했습니다', 4.5, default, 'Robert', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '기본 용품에 세면도구는 없네요 챙겨오세요', 3.5, default, 'Laura', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '다른 거는 좋았는데 주차가 별로였습니다', 4.5, default, 'Carol', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '주차랑 화장실이 별로에요', 3.0, default, 'Donna', 3, default);
insert into tblLodgingReview values(seqLodgingReview.nextVal, '근처에 행사가 있는지 힘들긴 했는데 직원분들 친철하고 좋습니다', 4.5, default, 'Edward', 3, default);

-- 좋아요
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Melissa', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Laura', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Ashley', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'aavid', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'David', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Joseph', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Thomas', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Brian', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Jennifer', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Carol', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Nancy', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'cavid', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Donna', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Robert', 3);
insert into tblLikeLodging values (seqLikeLodging.nextVal, 'Edward', 3);




