--관리자테이블

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
	pw varchar2(60) not null,
	gender varchar2(20) not null,
	profile varchar2(1000) default 'user.png' not null,
	regdate date default sysdate not null,
	active char(1) default 'y' not null

);

ALTER TABLE tblUser MODIFY profile VARCHAR2(1000) DEFAULT 'user.png';
alter table tblUser modify gender varchar2(20);

-- 여행지 (행정구역, 이미지)
create table tblCity (

	seq number primary key,
	name varchar2(30) not null,
	distrinct varchar2(30) not null,
   	 image varchar2(1000) default 'city.png' not null,
   	 mainaddress varchar2(1000) not null

);

select * from tblCity;

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
alter table tblPlan add author references tblUser(id) not null;


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

select * from tblFood;

create sequence seqFood;

ALTER TABLE tblFood MODIFY image VARCHAR2(1000) DEFAULT 'food.png';
alter table tblFood add lat number;
alter table tblFood add lng number;

--음식점 리뷰
create table tblFoodReview(
    seq number primary key,
    content varchar2(1000) not null,
    star number(2, 1) not null,
    regdate date default sysdate not null,
    id references tblUser(id) not null,
    fseq references tblFood(seq) not null,
    image varchar2(1000) null
    
);


create sequence seqFoodReview;

alter table tblFoodReview modify image varchar2(1000) null;

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

ALTER TABLE tblTour MODIFY image VARCHAR2(1000) DEFAULT 'tour.png';
alter table tblTour add lat number;
alter table tblTour add lng number;


create sequence seqTour;

drop table tblTourReview;

--관광명소 리뷰 테이블
create table tblTourReview (

    seq number primary key,
    content varchar2(1000) not null,
    star number(2,1) not null,
    regdate date default sysdate not null,
    tseq number not null references tblTour(seq),
    id varchar2(30) not null references tblUser(id),
    image varchar2(1000) null
    

);

select * from tabs;

create sequence seqTourReview;

alter table tblTourReview modify image varchar2(1000) null;

--숙소 카테고리
create table tblLodgingCategory (

	seq number primary key,
	category varchar2(100) not null

);

create sequence seqLodgingCategory;

drop table tblLodging;

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

alter table tblLodging add lat number;
alter table tblLodging add lng number;

create sequence seqLodging;



--숙소 리뷰


 create table tblLodgingReview (

   seq number primary key,
   content varchar2(1000) not null,
   star number(2,1) not null,
   regdate date default sysdate not null,
   id varchar2(30) references tblUser(id) not null,
   lseq number references tblLodging(seq) not null,
    image varchar2(1000) null
);


create sequence seqLodgingReview;

alter table tblLodgingReview modify image varchar2(1000) null;

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

alter table tblDailytour add regdate date default sysdate;
alter table tblDailyTour drop column time;

-- 하루일정 - 숙소
create table tblDailylodging(
    seq number primary key,
    dseq references tblDaily(seq) not null,
    lseq references tblLodging(seq) not null,
    time number default 30 not null
);

create sequence seqDailyLodging;

alter table tblDailylodging add regdate date default sysdate;
alter table tblDailyLodging drop column time;

-- 하루일정 - 음식점
create table tblDailyfood(
    seq number primary key,
    dseq references tblDaily(seq) not null,
    fseq references tblFood(seq) not null,
    time number default 30 not null
);

create sequence seqDailyFood;

alter table tblDailyfood add regdate date default sysdate;
alter table tblDailyFood drop column time;

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
drop table tblLikeTour;
drop sequence seqLikeTour;

CREATE TABLE tblLikeTour (
   seq number PRIMARY KEY,
    id varchar2(30) NOT NULL,
   tseq number NOT NULL REFERENCES tblTour(seq)
);

create sequence seqLikeTour;



--관심여행일정
drop table tblLikePlan;

CREATE TABLE tblLikePlan (
   seq number NOT NULL,
   id varchar2(30) NOT NULL,
   pseq   number NOT NULL REFERENCES tblPlan(seq)
);

create sequence seqLikePlan;


--관심 음식점
drop table tblLikeFood;

CREATE TABLE tblLikeFood (
   seq number NOT NULL,
   id varchar2(30) NOT NULL,
   fseq number NOT NULL REFERENCES tblFood(seq)
);

create sequence seqLikeFood;


--관심숙소
drop table tblLikeLodging;

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

select * from tabs;
create sequence seqPlanUser;


--추천여행지
create table tblRecommendCity (

	seq number primary key,
	sceq number not null references tblCity(seq)

);


create sequence seqRecommendCity;




