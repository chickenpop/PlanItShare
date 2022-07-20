
-- tour - 관광명소

-- 관광명소 select
select * from tblTour;
select * from tblCity;

-- 관광명소 조회
select
    t.placename,    -- 관관명소 이름
    t.address,      -- 관관명소 주소
    t.open,         -- 관관명소 운영시작시간
    t.close,        -- 관관명소 운영종료시간
    ci.name,        -- 여행지 이름
    tc.category     -- 관광명소 카테고리
 from tblTour t 
inner join tblCity ci on t.cseq = ci.seq
inner join tblTourCategory tc on t.tcseq = tc.seq;


-- 관심등록 테스트 insert
insert into tblLikeTour values (seqLikeTour.nextVal, 'chae', 1);
commit;

-- 인기 관광명소 목록조회
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
 
 
-- 인기 관광명소 단건조회
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
 where t.seq = 1 and c.seq = 1 order by likeCnt desc;


-- 관광명소 리뷰 테스트 insert
insert into tblTourReview (seq, content, star, tseq, id, image) values (seqTourReview.nextVal, '나쁘지 않았어요', 4.0, 1,'chae', null);
insert into tblTourReview (seq, content, star, tseq, id, image) values (seqTourReview.nextVal, '재밌었어요', 4.0, 2,'chae', null);
insert into tblTourReview (seq, content, star, tseq, id, image) values (seqTourReview.nextVal, '10시에 오시면 좋아요', 3.0, 1,'chae', null);



-- 관광명소 리뷰 삭제
select image from tblTourReview where seq = 42;
delete tblTourReview where seq = 23;


-- 관광명소 리뷰 조회

select  
       seq,
       content, 
       star,
       regdate,
       id,
       image
 from tblTourReview where tseq = 1 order by seq desc;


-- 관광명소 이름 검색
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
 where c.seq = 1 and t.placename like '%마%' order by likeCnt desc;

-- 관광명소 카테고리 검색
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
 where c.seq = 1 and tc.category like '%종교%' order by likeCnt desc;


-- 관광명소 찜
select seq, id, tseq from tblLikeTour;
insert into tblLikeTour (seq, id, tseq) values (seqLikeTour.nextVal, 'chae', 1);
rollback;

select count(*) as cnt from tblLikeTour where tseq = 1 and id = 'chae';

-- 숙소 관련 퀴리문(서영님)
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

commit;
 
select name from tblcity where seq = 1;
select likecnt from vwTour where seq = 1;
