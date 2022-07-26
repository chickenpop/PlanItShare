
-- plan - map

-- 필요한 테이블 정보
select * from tblPlan;
select * from tblUser;

select * from tblDaily order by seq;

select * from tblDailyTour;
select * from tblDailyLodging;
select * from tblDailyFood;

-- 일정 등록
-- id (chae) == author(chae)

insert into tblPlan (seq, cseq, regdate, readcount, startdate, enddate, title, content, author) 
values(seqPlan.nextVal, 1, default, default, to_date('22/07/20', 'yy/mm/dd'), to_date('22/07/22', 'yy/mm/dd'), '제주여행', '제주 2박 3일 여행기', 'chae');

-- 시퀀스, 일정번호, 여행일차
insert into tblDaily (seq, pseq, day) values(seqDaily.nextVal, 21, 1);
insert into tblDaily (seq, pseq, day) values(seqDaily.nextVal, 21, 2);
insert into tblDaily (seq, pseq, day) values(seqDaily.nextVal, 21, 3);

-- 하루일정 관광명소(일정 더미데이터)
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주 오설록 티뮤지엄', '제주 서귀포시 안덕면 신화역사로 15', '오설록.jpg', '09:00', '19:00', 2, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '누웨마루 거리', '제주 제주시 연동 연동7길', '누웨마루거리.jpg', '00:00', '23:59', 1, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주민속촌', '제주 서귀포시 표선면 민속해안로 631-34 63629', '제주민속촌.jpg', '09:30', '18:00', 10, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '성산 일출봉', '제주 서귀포시 성산읍 성산리 104', '성산일출봉.jpg', '07:30', '19:00', 8, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '신비의 도로', '제주 제주시 1100로 2894-63', '신비의도로.jpg', '00:00', '23:59', 9, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '한라산 국립공원', '제주 제주시 오등동 1100로 2070-61', '한라산.jpg', '09:00', '17:00', 4, 1);

--
select * from tblDailyTour;
select * from tblTour;

-- 번호, 하루일정번호, 관광명소 번호, 등록일자
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 1, 4, default);
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 1, 1, default);
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 2, 20, default);
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 2, 16, default);
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 3, 18, default);
insert into tblDailyTour (seq, dseq, tseq, regdate) values(seqDailyTour.nextVal, 3, 7, default);

--
select * from tblDailyLodging;
select * from tblLodging;

-- 하루일정 숙소(일정 더미데이터)
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '휘닉스 제주 섭지코지', '제주 서귀포시 성산읍 섭지코지로 107', 3, 1, '휘닉스.jpg', '14:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '646미터퍼세크', '제주 제주시 한경면 일주서로 4469 1,2,3층', 2, 1, '646미터퍼세크.jpg', '16:00', '11:00');


-- 번호, 하루일정번호, 숙소 번호, 등록일자
insert into tblDailylodging (seq, dseq, lseq, regdate) values(seqDailyLodging.nextVal, 1, 10, default);
insert into tblDailylodging (seq, dseq, lseq, regdate) values(seqDailyLodging.nextVal, 2, 6, default);

--
select * from tblDailyFood;
select * from tblFood;

-- 하루일정 음식점(일정 더미데이터)

INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '한경가든', '제주 제주시 한경면 노을해안로 1259 한경가든', '10:00', '20:00', 1, 1, '한경가든.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '몬스터살롱', '제주 제주시 애월읍 일주서로 6017 몬스터살롱', '11:00', '21:00', 3, 1, '몬스터살롱.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '하갈비', '제주 제주시 애월읍 애월북서길 52', '10:00', '22:00', 5, 1, '하갈비.png');

-- 번호, 하루일정번호, 음식점 번호, 등록일자

insert into tblDailyfood (seq, dseq, fseq, regdate) values(seqDailyFood.nextVal, 1, 4, default);
insert into tblDailyfood (seq, dseq, fseq, regdate) values(seqDailyFood.nextVal, 2, 12, default);
insert into tblDailyfood (seq, dseq, fseq, regdate) values(seqDailyFood.nextVal, 3, 19, default);


-- 여행일정 선택
select
       p.seq as seq, 
       p.cseq,
       p.readcount, 
       p.startdate,
       p.enddate,
       p.title,
       p.content,
       p.author
  from tblPlan p
 where seq = 1;

-- Daily 연결
select
       p.seq as seq, 
       c.name,
       p.readcount, 
       p.startdate,
       p.enddate,
       p.title,
       p.content,
       p.author,
       d.day
  from tblPlan p
 inner join tblCity c on p.cseq = c.seq
 inner join tblDaily d on d.pseq = p.seq
 inner join tblDailyTour dt on dt.dseq = d.seq
 inner join tblTour t on dt.tseq = t.seq
 where p.seq = 1;
 
-- 마지막 일차
select
       max(d.day) as lastDay
  from tblPlan p
 inner join tblCity c on p.cseq = c.seq
 inner join tblDaily d on d.pseq = p.seq
 inner join tblDailyTour dt on dt.dseq = d.seq
 inner join tblTour t on dt.tseq = t.seq
 where p.seq = 1;

-- 일정의 관광명소 위, 경도
select
       p.seq, 
       c.name,
       p.author,
       d.day,
       dt.regdate,
       t.placename,
       t.lat,
       t.lng
  from tblPlan p
 inner join tblCity c on p.cseq = c.seq
 inner join tblDaily d on d.pseq = p.seq
 inner join tblDailyTour dt on dt.dseq = d.seq
 inner join tblTour t on dt.tseq = t.seq
 where p.seq = 1;

-- 일정의 숙소 위,경도
select
       p.seq, 
       c.name,
       p.author,
       d.day,
       dl.regdate,
       l.name as placename,
       l.lat,
       l.lng
  from tblPlan p
 inner join tblCity c on p.cseq = c.seq
 inner join tblDaily d on d.pseq = p.seq
 inner join tblDailyLodging dl on dl.dseq = d.seq
 inner join tblLodging l on dl.lseq = l.seq
 where p.seq = 1;

-- 일정의 음식점 위,경도
select
       p.seq, 
       c.name,
       p.author,
       d.day,
       df.regdate,
       f.name as placename,
       f.lat,
       f.lng
  from tblPlan p
 inner join tblCity c on p.cseq = c.seq
 inner join tblDaily d on d.pseq = p.seq
 inner join tblDailyFood df on df.dseq = d.seq
 inner join tblFood f on df.fseq = f.seq
 where p.seq = 1;

-- 수아님 코드
select d.day, df.regdate, f.name, f.address, f.image, f.open, f.close, f.lat, f.lng, fc.category from tblDaily d inner join tblDailyFood df on df.dseq = d.seq inner join tblFood f on df.fseq = f.seq inner join tblFoodCategory fc on f.fcseq =fc.seq where pseq = 1;
select d.day, dl.regdate, l.name, l.address, l.image, l.checkin, l.checkout, l.lat, l.lng, lc.category from tblDaily d inner join tblDailyLodging dl on dl.dseq = d.seq inner join tblLodging l on dl.lseq = l.seq inner join tblLodgingCategory lc on l.lcseq =lc.seq where pseq = 1;
select d.day, dt.regdate, t.placename, t.address, t.image, t.open, t.close, t.lat, t.lng, tc.category from tblDaily d inner join tblDailyTour dt on dt.dseq = d.seq inner join tblTour t on dt.tseq = t.seq inner join tblTourCategory tc on t.tcseq =tc.seq where pseq = 1;











