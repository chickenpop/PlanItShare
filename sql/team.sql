
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
