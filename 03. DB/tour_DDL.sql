--tour_DDL
--localhost.team

--관리자테이블

select * from tabs;

create table tblAdmin (

	seq number primary key,
	name varchar2(20) not null,
	id varchar2(60) not null,
	pw varchar2(60) not null

);

create sequence seqAdmin;


--회원테이블
create table tblUser (

	id varchar2(60) primary key,
	name varchar2(20) not null,
	tel varchar2(20) not null,
    gender varchar2(1) not null,
	pw varchar2(60) not null,
	profile varchar2(1000) default 'user.png' not null,
	regdate date default sysdate not null,
	active char(1) default 'y' not null

);




--여행지 (행정구역, 이미지)
create table tblCity (

	seq number primary key,
	name varchar2(30) not null,
	distrinct varchar2(30) not null,
   	 image varchar2(1000) default 'city.png' not null,
   	 mainaddress varchar2(1000) not null

);



create sequence seqCity;



--일정
create table tblPlan (

	seq number primary key,
	cseq number references tblCity(seq) not null,
	regdate date default sysdate not null,
    readcount number default  0 not null,
    startdate date not null,
    enddate date not null,
    title varchar2(1000) not null,
    content varchar2(3000) not null

);


create sequence seqPlan;



--음식점 카테고리
create table tblFoodCategory(
    seq number primary key,
    category varchar2(30)
);

create sequence seqFoodCategory;


--음식점
create table tblFood(

    seq number primary key,
    name varchar2(300) not null,
    address varchar2(300) not null,
    open varchar2(20) not null,
    close varchar2(20) not null,
    fcseq number references tblFoodCategory(seq) not null,
    cseq number references tblCity(seq) not null,
    image varchar2(1000) default 'food.png' not null
    
);




create sequence seqFood;



--음식점 리뷰
create table tblFoodReview(
    seq number primary key,
    content varchar2(1000) not null,
    star number(2, 1) not null,
    regdate date default sysdate not null,
    id references tblUser(id) not null,
    fseq references tblFood(seq) not null,
    image varchar2(1000) default 'foodreview.png' not null
    
);





create sequence seqFoodReview;




--관광명소 카테고리 테이블
create table tblTourCategory (

    seq number primary key,
    category varchar2(100) not null

);

create sequence seqTourCategory;


--관광명소 테이블
create table tblTour (

    seq number primary key,
    placeName varchar2(100) not null,
    address varchar2(100) not null,
    open varchar2(20) not null,
    close varchar2(20) not null,
    tcseq number not null references tblTourCategory(seq),
    cseq number not null references tblCity(seq),
    image varchar2(1000) default 'tour.png' not null

);





create sequence seqTour;



--관광명소 리뷰 테이블
create table tblTourReview (

    seq number primary key,
    content varchar2(1000) not null,
    star number(2,1) not null,
    regdate date default sysdate not null,
    tseq number not null references tblTour(seq),
    id varchar2(30) not null references tblUser(id),
    image varchar2(1000) default 'tourreview.png' not null
    

);



create sequence seqTourReview;


--숙소 카테고리
create table tblLodgingCategory (

	seq number primary key,
	category varchar2(100) not null

);

create sequence seqLodgingCategory;




--숙소
 create table tblLodging (

	seq number primary key,
	name varchar2(100) not null,
	address varchar2(100) not null,
	lcseq number references tblLodgingCategory(seq) not null,
	cseq number references tblCity(seq) not null,
    image varchar2(1000) default 'lodging.png' not null,
    checkin varchar2(20) not null,
    checkout varchar2(20) not null

);






create sequence seqLodging;

--숙소 리뷰

drop table tblLodgingReview;

 create table tblLodgingReview (

   seq number primary key,
   content varchar2(1000) not null,
   star number(2,1) not null,
   regdate date default sysdate not null,
   id varchar2(30) references tblUser(id) not null,
    lseq number references tblLodging(seq) not null,
    image varchar2(1000) default 'lodgingreview.png' not null
);


create sequence seqLodgingReview;


-- 하루 일정
create table tblDaily (
    seq number primary key,
    pseq references tblPlan(seq) not null,
    startdate date not null,
    enddate date not null,
    day number not null
);


create sequence seqDaily;

-- 하루일정 - 관광명소 > time 분단위
create table tblDailytour(
    seq number primary key,
    dseq references tblDaily(seq) not null,
    tseq references tblTour(seq) not null,
    time number default 30 not null
);

create sequence seqDailyTour;


-- 하루일정 - 숙소
create table tblDailylodging(
    seq number primary key,
    dseq references tblDaily(seq) not null,
    lseq references tblLodging(seq) not null,
    time number default 30 not null
);

create sequence seqDailyLodging;


-- 하루일정 - 음식점
create table tblDailyfood(
    seq number primary key,
    dseq references tblDaily(seq) not null,
    fseq references tblFood(seq) not null,
    time number default 30 not null
);

create sequence seqDailyFood;


-- 댓글
create table tblComment(
    seq number primary key,
    content varchar2(1000) not null,
    regdate date default sysdate not null,
    id varchar2(60) references tblUser(id) not null,
    pseq references tblPlan(seq) not null
);


create sequence seqComment;



--금지어
create table tblBannedWord(
    seq number primary key,
    word varchar2(50) not null
);


create sequence seqBannedWord;


--관심관광명소

CREATE TABLE tblLikeTour (
	seq number PRIMARY KEY,
	id varchar2(30) NOT NULL,
	tseq number NOT NULL REFERENCES tblTour(seq)
);


create sequence seqLikeTour;



--관심여행일정
CREATE TABLE tblLikePlan (
	seq number NOT NULL,
	id varchar2(30) NOT NULL,
	pseq	number NOT NULL REFERENCES tblPlan(seq)
);

create sequence seqLikePlan;


--관심 음식점
CREATE TABLE tblLikeFood (
	seq number NOT NULL,
	id varchar2(30) NOT NULL,
	fseq number NOT NULL REFERENCES tblFood(seq)
);



drop table tblLikeLodging;

--관심숙소
CREATE TABLE tblLikeLodging (
	seq number NOT NULL,
	id varchar2(30) NOT NULL,
	lseq	number NOT NULL REFERENCES tblLodging(seq)
);



create sequence seqLikeLodging;


--초대
create table tblInvitation (

	seq number primary key,
	pseq number not null references tblPlan(seq),
	host varchar2(60) not null references tblUser(id),
    guest varchar2(60) not null references tblUser(id),
	regdate date default sysdate not null

);


create sequence seqInvitation;


--일정-유저 

create table tblPlanUser (

	seq number primary key,
	id varchar2(60) not null references tblUser(id),
	pseq number not null references tblPlan(seq)

);


create sequence seqPlanUser;


--추천여행지
create table tblRecommendCity (

	seq number primary key,
	sceq number not null references tblCity(seq)

);

create sequence seqRecommendCity;

create sequence seqLikeFood;
create sequence seqLikePlan;
create sequence seqPlan;

ALTER TABLE tblUser MODIFY profile VARCHAR2(1000) DEFAULT 'user.png';
ALTER TABLE tblFood MODIFY image VARCHAR2(1000) DEFAULT 'food.png';
ALTER TABLE tblFoodReview MODIFY image VARCHAR2(1000) DEFAULT 'foodreview.png';

alter table tblFoodReview modify image varchar2(1000) null;
alter table tblLodgingReview modify image varchar2(1000) null;
alter table tblTourReview modify image varchar2(1000) null;


alter table tblDailytour add regdate date default sysdate;
alter table tblDailylodging add regdate date default sysdate;
alter table tblDailyfood add regdate date default sysdate;

alter table tblTour add lat number;
alter table tblTour add lng number;

alter table tblFood add lat number;
alter table tblFood add lng number;

alter table tblLodging add lat number;
alter table tblLodging add lng number;

alter table tblUser modify gender varchar2(20);

alter table tblPlan add author references tblUser(id) not null;

alter table tblDailyFood drop column time;
alter table tblDailyLodging drop column time;
alter table tblDailyTour drop column time;

alter table tblDaily drop column startdate;
alter table tblDaily drop column enddate;

alter table tblUser modify profile varchar2(100) default 'user.png';



-- 
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
from tblPlan p inner join tblCity c on p.cseq = c.seq order by likecnt desc ;


create or replace view vwmyplan
as
select
    c.image, p.title, pu.id, p.readcount,
    (select count(*) From tbllikeplan lp where lp.pseq = p.seq) as likecnt
from tbluser u
    inner join tblLikePlan lp
        on u.id = lp.id
            inner join tblPlan p
                on p.seq = lp.pseq
                    inner join tblCity c
                        on c.seq = p.cseq
                            inner join tblplanuser pu
                                on pu.pseq = p.seq;
                      



--숙소 관심뷰
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
        




-- 여행지 뷰
create or replace view vwTour
as
select
       t.seq, 
       t.placename, 
       t.address, 
       t.open, 
       t.close, 
       t.image, 
       t.cseq, 
       tc.category, 
       (select count(*) from tblLikeTour lt where lt.tseq = t.seq) as likeCnt, 
       (select count(*) from tblTourReview tr where tr.tseq = t.seq) as reviewCnt, 
       (select round(avg(tr.star), 2) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg
  from tblTour t 
 inner join tblCity c on t.cseq = c.seq
 inner join tblTourCategory tc on t.tcseq = tc.seq 
 where c.seq = 1 order by likeCnt desc;



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
                inner join tblLodgingReview lr on lr.lseq = l.seq order by seq desc);   

select * from tblLodgingReview;

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
                inner join tblFoodReview fr on fr.fseq = f.seq order by seq desc);


-- 음식점  
CREATE or REPLACE VIEW vwFood 
as
select
f.name, f.address, f.image, fc.category,
(select count(*) from tblLikeFood lf where lf.fseq = f.seq) as likecnt, 
(select count(*) from tblFoodReview fr where fr.fseq = f.seq) as reviewcnt,
(select avg(star) from tblFoodReview fr where fr.fseq = f.seq) as reviewavg
from tblFood f
    inner join tblCity c
        on f.cseq = c.seq
        inner join tblFoodCategory fc
            on f.fcseq = fc.seq 
                where c.seq = 1 
                    order by likecnt desc;

                
                
CREATE or REPLACE VIEW vwmyPlan as
select
    c.image, p.title, u.id, p.readcount,
    (select count(*) From tbllikeplan lp where lp.pseq = p.seq) as likecnt
from tbluser u
    inner join tblLikePlan lp
        on u.id = lp.id
            inner join tblPlan p
                on p.seq = lp.pseq
                    inner join tblCity c
                        on c.seq = p.cseq
                            inner join tblplanuser pu
                                on pu.pseq = p.seq;
                               
                

-- 숙소
CREATE or REPLACE VIEW vwLodging
as
SELECT
    l.name, l.address, l.image, lc.category,
    (select count(*) from tbllikelodging ll where ll.lseq = l.seq) as likecnt,
    (select count(*) from tbllodgingreview lr where lr.lseq = l.seq) as reviewcnt,
    (select count(star) from tbllodgingreview lr where lr.lseq = l.seq) as reviewavg
FROM tbllodging l
    inner join tblcity c
        on l.cseq = c.seq
            inner join tbllodgingcategory lc
                on lc.seq = l.lcseq
                    where c.seq =1;

select count(*) as cnt from tblfood;

SELECT * From (select a.*, rownum as rnum from vwFood a) where rnum BETWEEN 1 and 10;


-- 관심여행일정
CREATE or REPLACE VIEW vwLikePlan as
select
    c.image, p.title, u.name, p.readcount,
    (select count(*) From tbllikeplan lp where lp.pseq = p.seq) as likecnt
from tbluser u
    inner join tblLikePlan lp
        on u.id = lp.id
            inner join tblPlan p
                on p.seq = lp.pseq
                    inner join tblCity c
                        on c.seq = p.cseq
                                    where c.seq = 1;
               
            
               