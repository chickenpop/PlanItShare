--tour_DML.sql


--tblUser
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('yoon', '강지윤', '01011112222', '1111', default, default, 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('kim', '김시현', '01022682957', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('suea724', '안수아', '01000000000', '1111', default, default, 'y', 'f');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('baek', '백서영', '01011111111', '1111', default, default, 'y', 'f'); 
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('ckdgus1999', '변창현', '01080713126', '1111', default, default, 'y', 'm');
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('chae', '박채은', '01043219876', '1111', default, default, 'y', 'f'); 
insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test', '테스트', '01012345678', '1111', default, default, 'y', 'm'); 

--tblAdmin
insert into tblAdmin(seq, name, id, pw) values (seqAdmin.nextVal, '관리자', 'admin', '1111');





-- 여행지
-- 대표 여행지 (서울, 제주, 부산, 경주, 진주, 강릉, 여수, 전주)
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '제주', '주요도시', '제주.jpg', '제주특별자치도 제주시 문연로 6');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '서울', '주요도시', '서울.jpg', '서울특별시 중구 세종대로 110');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '부산', '주요도시', '부산.jpg', '부산광역시 연제구 중앙대로 1001');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '경주', '주요도시', '경주.jpg', '경상북도 경주시 양정로 260');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '진주', '주요도시', '진주.jpg', '경상남도 진주시 동진로 155');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '강릉', '주요도시', '강릉.jpg', '강릉시 강릉대로 33');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '여수', '주요도시', '여수.jpg', '전라남도 여수시 시청로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '전주', '주요도시', '전주.jpg', '전라북도 전주시 완산구 노송광장로 10');




-- 강원
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '동해', '강원도', default, '동해시 천곡로 77');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '속초', '강원도', default, '속초시 중앙로 183');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '양양', '강원도', default, '양양군 양양읍 군청길 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '영월', '강원도', default, '영월군 영월읍 하송로 64');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '원주', '강원도', default, '원주시 시청로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '춘천', '강원도', default, '춘천시 시청길 11');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '평창', '강원도', default, '평창군 평창읍 군청길 77');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '횡성', '강원도', default, '횡성군 횡성읍 태기로 15');

-- 경기도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '가평', '경기도', default, '경기도 가평군 가평읍 석봉로 181');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '구리', '경기도', default, '경기도 구리시 아차산로');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '김포', '경기도', default, '경기도 김포시 사우중로');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '남양주', '경기도', default, '경기도 남양주시 경춘로');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '부천', '경기도', default, '경기도 부천시 길주로');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '수원', '경기도', default, '경기도 수원시 팔달구 효원로 241');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '시흥', '경기도', default, '경기도 시흥시 시청로 20');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '용인', '경기도', default, '경기도 용인시 처인구 중부대로 1199');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '의정부', '경기도', default, '경기도 의정부시 시민로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '파주', '경기도', default, '경기도 파주시 시청로 50');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '화성', '경기도', default, '경기도 화성시 남양읍 시청로 159');

-- 경상남도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '거제', '경상남도', default, '경상남도 거제시 계룡로 125');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '고성', '경상남도', default, '경상남도 고성군 고성읍 성내로 130');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '김해', '경상남도', default, '경상남도 김해시 김해대로 2401');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '남해', '경상남도', default, '경상남도 남해군 망운로 9번길 12');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '양산', '경상남도', default, '경상남도 양산시 중앙로 39');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '의령', '경상남도', default, '경상남도 의령군 의령읍 충익로 63');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '통영', '경상남도', default, '경상남도 통영시 통영해안로 515');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '하동', '경상남도', default, '경상남도 하동군 하동읍 군청로 23');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '함양', '경상남도', default, '경상남도 함양군 함양읍 고운로 35');

-- 경상북도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '성주', '경상북도', default, '경상북도 성주군 성주읍 성주로 3200');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '안동', '경상북도', default, '경상북도 안동시 퇴계로 115');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '의성', '경상북도', default, '경상북도 의성군 의성읍 군청길 31');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '포항', '경상북도', default, '경상북도 포항시 남구 시청로 1');

-- 전라남도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '담양', '전라남도', default, '전라남도 담양군 담양읍 추성로 1371');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '목포', '전라남도', default, '전라남도 목포시 양을로 203');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '보성', '전라남도', default, '전라남도 보성군 보성읍 송재로 165');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '순천', '전라남도', default, '전라남도 순천시 장명로 30');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '완도', '전라남도', default, '전라남도 완도군 완도읍 청해진남로 51');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '해남', '전라남도', default, '전라남도 해남군 해남읍 군청길 4');

-- 전라북도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '군산', '전라북도', default, '전라북도 군산시 시청로 17');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '김제', '전라북도', default, '전라북도 김제시 중앙로 40');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '남원', '전라북도', default, '전라북도 남원시 시청로 60');

-- 충청남도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '공주', '충청남도', default, '충청남도 공주시 봉황로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '논산', '충청남도', default, '충청남도 논산시 시민로210번길 9');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '당진', '충청남도', default, '충청남도 당진시 시청1로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '보령', '충청남도', default, '충청남도 보령시 성주산로 77');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '부여', '충청남도', default, '충청남도 부여군 부여읍 사비로 33 부여군청');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '서천', '충청남도', default, '충청남도 서천군 서천읍 군청로 57');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '아산', '충청남도', default, '충청남도 아산시 시민로 456');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '천안', '충청남도', default, '충청남도 천안시 서북구 번영로 156');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '태안', '충청남도', default, '충청남도 태안군 태안읍 군청로 1');


-- 충청북도
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '괴산', '충청북도', default, '충청북도 괴산군 괴산읍 임꺽정로 90');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '단양', '충청북도', default, '충청북도 단양군 단양읍 중앙1로 10');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '영동', '충청북도', default, '충청북도 영동군 영동읍 동정로 1');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '음성', '충청북도', default, '충청북도 음성군 음성읍 중앙로 173');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '제천', '충청북도', default, '충청북도 제천시 내토로 295');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '청주', '충청북도', default, '충청북도 청주시 상당구 상당로69번길 38');
insert into tblcity(seq, name, distrinct, image, mainaddress) values (seqCity.NEXTVAL, '충주', '충청북도', default, '충청북도 충주시 으뜸로 21');






-- 관광명소 카테고리
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '랜드마크');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '전시관');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '공원');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '자연');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '유적지');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '라이프스타일');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '종교 성지');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '야외 스포츠 시설');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '관광 투어');
insert into tblTourCategory(seq, category) values (seqTourCategory.nextVal, '전통/민속 체험');


--숙소카테고리
--tblLodgingCategory
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '호텔');
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '펜션');
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '리조트');
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '모텔');
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '게스트하우스');
insert into tblLodgingCategory (seq, category) values (seqLodgingCategory.nextVal, '에어비앤비');


--음식점 카테고리
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '한식');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '중식');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '양식');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '일식');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '고기');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '분식');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '패스트푸드');
INSERT INTO tblFoodCategory VALUES(seqFoodCategory.NEXTVAL, '디저트');







--관광명소
-- 랜드마크
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '누웨마루 거리', '제주 제주시 연동 연동7길', '누웨마루거리.jpg', '00:00', '23:59', 1, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주 러브랜드', '제주 제주시 연동로 1100(천백)로 2894-72', '제주러브랜드.jpg', '09:00', '23:59', 1, 1);

-- 전시관
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '테디베어 박물관', '제주 서귀포시 색달동 2889', '테디베어박물관.jpg', '09:00', '18:00', 2, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주 오설록 티뮤지엄', '제주 서귀포시 안덕면 신화역사로 15', '오설록.jpg', '09:00', '19:00', 2, 1);

-- 공원
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '에코랜드 테마파크', '제주 제주시 조천읍 번영로 1278-169', '에코랜드.jpg', '09:00', '17:00', 3, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '여미지 식물원', '제주 서귀포시 색달동 2920번지 697-808', '여미지식물원.jpg', '09:00', '18:00', 3, 1);

-- 자연
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '한라산 국립공원', '제주 제주시 오등동 1100로 2070-61', '한라산.jpg', '09:00', '17:00', 4, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '용두암', '제주 제주시 용담2동 용두암길 15', '용두암.jpg', '00:00', '23:59', 4, 1);

-- 유적지
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '관덕정', '제주 제주시 관덕로 19', '관덕정.jpg', '09:00', '18:00', 5, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '성읍 민속마을', '제주 제주시 표선면 성읍리 699-912', '성읍민속마을.jpg', '00:00', '23:59', 5, 1);

-- 라이프 스타일
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주신화월드', '제주 서귀포시 안덕면 신화역사로 304번길 38', '제주신화월드.jpg', '10:00', '20:00', 6, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '산방산 탄산온천', '제주 서귀포시 안덕면 사계북로41번길 192', '산방산탄산온천.jpg', '10:00', '20:00', 6, 1);

-- 종교 성지
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '관음사', '제주 제주시 아라동 387, 690-121', '관음사.jpg', '00:00', '23:59', 7, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '약천사', '제주 서귀포시 중문동 이어도로 293-28', '약천사.jpg', '00:00', '23:59', 7, 1);

-- 야외 스포츠 시설
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '씨워커코리아', '제주 제주시 도두이동297-2', '씨워커코리아.jpg', '09:00', '22:00', 8, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '성산 일출봉', '제주 서귀포시 성산읍 성산리 104', '성산일출봉.jpg', '07:30', '19:00', 8, 1);

--관광 투어
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '사려니숲길', '제주 서귀포시 성산읍 성산리 104', '사려니숲길.jpg', '09:00', '17:00', 9, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '신비의 도로', '제주 제주시 1100로 2894-63', '신비의도로.jpg', '00:00', '23:59', 9, 1);

-- 전통/민속 체험
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '김경숙 해바라기', '제주 제주시 회천동 391', '김경숙해바라기.jpg', '09:00', '19:00', 10, 1);
insert into tblTour(seq, placeName, address, image, open, close, tcseq, cseq) values (seqTour.nextVal, '제주민속촌', '제주 서귀포시 표선면 민속해안로 631-34 63629', '제주민속촌.jpg', '09:30', '18:00', 10, 1);




--tblLodging
--1.호텔
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '제주신라호텔', '제주 서귀포시 중문관광로72번길 75', 1, 1, '신라호텔.jpg', '14:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '롯데호텔 제주', '제주 서귀포시 중문관광로72번길 35 롯데호텔 제주', 1, 1, '롯데호텔.jpg', '15:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '메종 글래드 제주', '제주 제주시 노연로 80', 1, 1, '메종글래드제주.jpg', '15:00', '12:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '호텔휘슬락', '제주 제주시 서부두2길 26', 1, 1, '휘슬락호텔.jpg', '15:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '서귀포칼호텔', '제주 서귀포시 색달중앙로252번길 124', 1, 1, '서귀포칼호텔.jpg', '14:00', '12:00');



--2.펜션
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '646미터퍼세크', '제주 제주시 한경면 일주서로 4469 1,2,3층', 2, 1, '646미터퍼세크.jpg', '16:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '인별', '제주 제주시 구좌읍 행원로1길 25', 2, 1, '인별.jpg', '16:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '라라스테이', '제주 제주시 한림읍 협재1길 55-3', 2, 1, '라라스테이.jpg', '15:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '집의기록 part1', '제주 제주시 한림읍 귀덕11길 60 집의기록 part1', 2, 1, '집의기록.jpg', '16:00', '11:00');
    
--3.리조트
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '휘닉스 제주 섭지코지', '제주 서귀포시 성산읍 섭지코지로 107', 3, 1, '휘닉스.jpg', '14:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '금호제주리조트', '제주 서귀포시 남원읍 태위로 522-12 금호제주콘도미니엄', 3, 1, '금호제주리조트.jpg', '14:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '한화리조트제주', '제주 제주시 명림로 575-107', 3, 1, '한화리조트제주.jpg', '14:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '롯데아트빌라스', '제주 서귀포시 색달중앙로252번길 124', 3, 1, '롯데아트빌라스.jpg', '15:00', '11:00');

--4.모텔
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '더엠모텔', '제주 제주시 전농로 59', 4, 1, '더엠모텔.jpg', '15:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '아마빌레', '제주 제주시 애월읍 애월해안로 909', 4, 1, '아마빌레.jpg', '15:00', '11:00');


--5.게스트하우스
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '백패커스홈', '제주 서귀포시 중정로 24-7 1층', 5, 1, '백패커스홈.jpg', '16:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) 
    values (seqLodging.nextVal, '오션뷰제주 게스트하우스', '제주 서귀포시 안덕면 난드르로 49-71 오션뷰제주 게스트하우스', 5, 1, '오션뷰제주게스트하우스.jpg', '17:00', '11:00');

--6.에어비앤비
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) values (seqLodging.nextVal, '제주고옥', '제주 제주시 한림읍 귀덕리', 6, 1, '제주고옥.jpg', '16:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) values (seqLodging.nextVal, '제주이티하우스', '제주 제주시 애월읍 구엄4길 20-9', 6, 1, '이티하우스.jpg', '15:00', '11:00');
insert into tblLodging (seq, name, address, lcseq, cseq, image, checkin, checkout) values (seqLodging.nextVal, '뿌라비다', '제주 제주시 서해안로 518', 6, 1, '뿌라비다.jpg', '15:00', '11:00');



--음식
-- 한식
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '백리향', '제주 제주시 조천읍 신북로 244', '10:00', '20:00', 1, 1,'백리향.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '애월 우니담', '제주 제주시 애월읍 고내로13길 107 2층', '10:00', '20:00', 1, 1, '우니담.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '임금님 밥상', '제주 제주시 오광로 128', '10:00', '20:00', 1, 1, '임금님밥상.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '한경가든', '제주 제주시 한경면 노을해안로 1259 한경가든', '10:00', '20:00', 1, 1, '한경가든.png');

-- 중식
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '면차롱', '제주 제주시 한림읍 한림로 380', '11:00', '19:00', 2, 1,'면차롱.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '아서원', '제주 서귀포시 칠십리로 699', '11:00', '19:00', 2, 1, '아서원.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '덕성원', '제주 서귀포시 태평로401번길 4', '11:00', '19:00', 2, 1, '덕성원.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '홍성방', '제주 서귀포시 대정읍 하모항구로 76', '11:00', '19:00', 2, 1, '홍성방.png');

-- 양식
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '라움이즈', '제주 서귀포시 대정읍 중산간서로 2242 3층', '11:00', '21:00', 3, 1,'라움이즈.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '피즈 애월', '제주 제주시 애월읍 애월로 29 1층 피즈', '11:00', '21:00', 3, 1, '피즈애월.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '황셰프 양식당', '제주 제주시 한림읍 귀덕로 91 가동 1층', '11:00', '21:00', 3, 1, '황셰프 양식당.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '몬스터살롱', '제주 제주시 애월읍 일주서로 6017 몬스터살롱', '11:00', '21:00', 3, 1, '몬스터살롱.png');


-- 일식
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '로드 129', '제주 제주시 애월읍 애월로 129', '11:00', '21:00', 4, 1,'로드 129.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '모루쿠다', '제주 서귀포시 태평로431번길 32 1층 모루쿠다', '11:00', '21:00', 4, 1, '모루쿠다.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '온오프', '제주 제주시 우도면 우도해안길 876 온오프', '11:00', '21:00', 4, 1, '온오프.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '스시애월', '제주 제주시 애월읍 애월로9길 50-3', '11:00', '21:00', 4, 1, '스시애월.png');

-- 고기
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '숙성도 중문점', '제주 서귀포시 일주서로 966', '10:00', '22:00', 5, 1,'숙성도 중문점.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '돈사돈', '제주 제주시 우평로 19', '10:00', '22:00', 5, 1, '돈사돈.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '하갈비', '제주 제주시 애월읍 애월북서길 52', '10:00', '22:00', 5, 1, '하갈비.png');

-- 분식
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '제주 김만복 본점', '제주 제주시 오라로 41', '10:00', '22:00', 6, 1,'제주 김만복 본점.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '노라바', '제주 제주시 애월읍 구엄길 100', '10:00', '22:00', 6, 1, '노라바.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '짱구분식', '제주 서귀포시 중동로48번길 3', '10:00', '22:00', 6, 1, '짱구분식.png');

--패스트푸드
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '맥도날드 제주 노형점', '제주 제주시 노연로 7', '00:00', '24:00', 7, 1,'맥도날드 제주 노형점.png');


-- 디저트
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '만감교차', '제주 서귀포시 성산읍 환해장성로 950', '10:00', '22:00', 8, 1,'만감교차.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '델문도', '제주 제주시 조천읍 조함해안로 519-10', '10:00', '22:00', 8, 1, '델문도.png');
INSERT INTO tblFood VALUES(seqFood.NEXTVAL, '마마롱', '제주 제주시 애월읍 평화로 2783 1층 마마롱', '10:00', '22:00', 8, 1, '마마롱.png');


-- 일정
insert into tblPlan values(seqPlan.nextVal, 1, sysdate, 35, '2022-05-03', '2022-06-02', '혼자 즐기는 제주 한달 여행', '퇴사 후 얻게 된 자유를 제주에서 만끽해야지! 이곳저곳 갈 곳이 많아서 꼼꼼히 정리해야겠다.','baek');
insert into tblPlan values(seqPlan.nextVal, 3, sysdate, 64, '2022-06-10', '2022-06-13', '설레는 부산 여행', '해운대, 광안리 바다를 볼 생각만 해도 벌써 설렌다', 'kim');
insert into tblPlan values(seqPlan.nextVal, 6, sysdate, 75, '2022-04-18', '2022-04-20', '강릉 온전히 즐기기', '강릉엔 커피가 유명해서 유명한 커피들을 다 경험해보는게 목표!', 'suea724');
insert into tblPlan values(seqPlan.nextVal, 8, sysdate, 135, '2022-01-22', '2022-01-23', '전주 먹거리 탐방!!', '콩나물해장국, 전주비빔밥, 전주초코파이..다먹고와야지 ~~', 'yoon');
insert into tblPlan values(seqPlan.nextVal, 5, sysdate, 53, '2022-03-19', '2022-03-19', '진주 당일치기 여행', '진주는 처음 방문해보는 곳인데 어떤곳일지 너무 기대된다!', 'ckdgus1999');
insert into tblPlan values(seqPlan.nextVal, 2, sysdate, 25, '2022-08-10', '2022-08-13', '서울 방방곡곡 여행', '인터넷에서만 보던 맛집과 카페 다 가보는게 목표이다!ㅎㅎ', 'chae');
insert into tblPlan values(seqPlan.nextVal, 4, sysdate, 58, '2022-04-05', '2022-04-08', '경주 문화 유적지 투어', '경주 곳곳을 누비며 역사와 함께하는 시간을 보낼 계획이다', 'test');
insert into tblPlan values(seqPlan.nextVal, 1, sysdate, 101, '2022-05-03', '2022-05-07', '제주에서 힐링하기', '제주도에서 온전히 휴식을 취하고자 기획한 힐링여행이다.', 'baek');
insert into tblPlan values(seqPlan.nextVal, 1, sysdate, 80, '2022-02-03', '2022-02-05', '가족이랑 제주 여행', '가족이랑 함께 즐기기 좋은 제주도 일정을 기획해보았다.', 'yoon');


-- 이미지 이름 수정
UPDATE "PROJECTACC"."TBLFOOD" SET IMAGE = '애월 우니담.png';
UPDATE "PROJECTACC"."TBLFOOD" SET IMAGE = '임금님 밥상.png';
UPDATE "PROJECTACC"."TBLFOOD" SET IMAGE = '오는정 김밥.jpg';

커밋 성공

select * from tblCity;

UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '제주신라호텔.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '롯데호텔 제주.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '메종 글래드 제주.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '호텔휘슬락.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '서귀포칼호텔.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '646미터퍼세크.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '인별.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '라라스테이.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '집의기록.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '휘닉스제주 섭지코지.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '금호제주리조트.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '한화리조트제주.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '롯데아트빌라스.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '더엠모텔.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '아마빌레.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '백패커스홈.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '오션뷰제주 게스트하우스.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '제주고옥.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '이티하우스.png';
UPDATE "PROJECTACC"."TBLLODGING" SET IMAGE = '뿌라비다.png';

커밋 성공


UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '누웨마루거리.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '제주 러브랜드.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '테디베어 박물관.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '제주 오설록 티뮤지엄.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '에코랜드 테마파크.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '여미지 식물원.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '한라산 국립공원.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '용두암.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '관덕정.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '성읍 민속마을.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '제주신화월드.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '산방산 탄산온천.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '관음사.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '약천사.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '씨워커코리아.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '성산 일출봉.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '사려니숲길.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '신비의 도로.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '김경숙 해바라기.png';
UPDATE "PROJECTACC"."TBLTOUR" SET IMAGE = '제주민속촌.png';



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

select * from tblLikeLodging;
select * from tblLodging;

insert into tblLikeLodging values(seqLikeLodging.nextVal, 'aavid', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Devid', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'David', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Robert', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Michael', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Charles', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Joseph', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Thomas', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Edward', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Andrew', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Brian', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Kevin', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'cavid', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Joshua', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Jennifer', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Carol', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Ashley', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Melissa', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Laura', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Donna', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'test', 1);
insert into tblLikeLodging values(seqLikeLodging.nextVal, 'Nancy', 1);

select * from tblLikePlan;

-- 76 79 102 103

insert into tblLikePlan values (seqLikePlan.nextVal, 'Edward', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Nancy', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Donna', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Jennifer', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Andrew', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Charles', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Robert', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Devid', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Melissa', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'suea724', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'baek', 76);
insert into tblLikePlan values (seqLikePlan.nextVal, 'yoon', 76);

insert into tblLikePlan values (seqLikePlan.nextVal, 'Edward', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Nancy', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Donna', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Jennifer', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Andrew', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Charles', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Robert', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Devid', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Melissa', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'suea724', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'chae', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'baek', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'kim', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'test', 79);
insert into tblLikePlan values (seqLikePlan.nextVal, 'aavid', 79);

insert into tblLikePlan values (seqLikePlan.nextVal, 'Edward', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Nancy', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Donna', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Jennifer', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Andrew', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Charles', 102);
insert into tblLikePlan values (seqLikePlan.nextVal, 'Robert', 102);


insert into tblLikePlan values(seqLikePlan.nextVal, 'Edward', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Nancy', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Donna', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Jennifer', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Andrew', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Charles', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Robert', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Devid', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Melissa', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'suea724', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'test', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'David', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'yoon', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'kim', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'baek', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'chae', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Joseph', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'aavid', 103);
insert into tblLikePlan values(seqLikePlan.nextVal, 'Carol', 103);

