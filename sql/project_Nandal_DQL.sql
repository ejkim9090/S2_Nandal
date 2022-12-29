--메인페이지 리뷰 추천유형 조회
select CLASS_IMG, CLASS_NAME, CLASS_ADDRESS, CLASS_PRICE
    from CLASS 
    where class_code in (select ca.CLASS_CODE
                                from review r join CLASS_APPLY ca on r.REVIEW_CODE = ca.CA_CODE
                                where r.REVIEW_GROUP = 3)
;
--메인 페이지 노출 리뷰 목록 -- distinct은 중복 제거 first_value는 해당 데이터의 첫번째 행인데 partition by로 그룹핑하면 해당 그룹에 첫번째
select  distinct first_value(rp.RP_ROUTE)  over(partition by review_code) a, r.REVIEW_CONT, REVIEW_CODE, ca.class_code
    from review r join review_photo rp using(review_code)
                        join CLASS_APPLY ca on review_code = ca.ca_code
     where review_grade > 4
;

--메인,목록 페이지의 클래스 목록 표시 정보
select CLASS_IMG, CLASS_NAME, AREA_NAME, CLASS_PRICE
    from CLASS join AREA using(AREA_CODE)
    where CLASS_NAME = '키워드' or CLASS_INTRO = '키워드';
    
    select CLASS_IMG, CLASS_NAME, CLASS_ADDRESS, CLASS_PRICE
    from CLASS 
    where CLASS_NAME like '%크리스마스%';
    
    --위 정보에 리뷰 평균 점수 및 리뷰 개수 추가
select ROUND(allavg,1) allavg, allcnt, CLASS_IMG, CLASS_NAME, CLASS_ADDRESS, CLASS_PRICE
    from class c left join (select ca.CLASS_CODE, avg(r.REVIEW_GRADE) allavg , count(r.review_GRADE) allcnt
                                        from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE
                                         group by ca.class_code) ca on c.class_code = ca.class_code
    where c.class_name like '%크리스마스%'
;
select count(*) cnt from class  where class_name like '%크리스마스%';
select * 
    from (select t1.*, rownum r 
                from  (select ROUND(allavg,1) allavg, allcnt, c.class_code ,c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
                                    from class c left join (select ca.CLASS_CODE, avg(r.REVIEW_GRADE) allavg , count(r.review_GRADE) allcnt
                                        from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE
                                         group by ca.class_code) ca on c.class_code = ca.class_code
                            where 1=1 and class_price between 0 and 999999999 order by c.class_name asc) t1 ) t2 where r between ? and ?
--카테고리 선택 시 클래스 정보
select c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADRESS, c.CLASS_PRICE
    from CLASS c join CATEGORY cg using(category_code)
    where cg.category_name = '카테고리';
--키워드 검색 시 
select CLASS_IMG, CLASS_NAME, CLASS_ADRESS, CLASS_PRICE
    from CLASS 
    where CLASS_NAME = '키워드';
--유형 선택 시
select c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADRESS, c.CLASS_PRICE
    from CLASS c join AREA a using(AREA_CODE) 
                join CATEGORY cg using(category_code)
                join CLASS_SCHEDULE cs using(class_code)
    where a.area_name = '지역' 
        and cg.category_name = '카테고리' 
        and c.class_level = 1
        and cs.cs_day = 1
;
--키워드 + 유형 선택 시
select * --c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
;
-- 인기순, 평점순, 가격순에 따른 정렬
select * --c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
        
        
        order by (select count(ca.class_code) cnt
                     from class c2 left join (select * from CLASS_apply where ca_cancel = 'N') ca on c2.CLASS_CODE = ca.class_code
                     where  c2.class_code = c.class_code
                     group by c2.class_code) desc
;
-- 인기순 - 각 클래스 별 신청 접수(취소한 신청 제외)가 많은 순으로 정렬
select c.class_code, count(ca.class_code) cnt
    from class c left join (select * from CLASS_apply where ca_cancel = 'N') ca on c.CLASS_CODE = ca.class_code
    group by c.class_code
    order by cnt desc
; 
-- 높은 평점순 정렬
-- 1.각 리뷰에 총점 가져오기
select REVIEW_GRADE
    from review
;
-- 2.해당 리뷰를 가진 취소가 아닌 신청 내역의 클래스 코드 별 평균 점수 가져오기
select ca.CLASS_CODE, avg(r.REVIEW_GRADE)
    from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE
    group by ca.class_code
;
--3.평균 점수별 class_code 순으로 클래스 정렬
select allavg, c4.class_name
    from class c4 left join (select ca3.CLASS_CODE, avg(r.REVIEW_GRADE) allavg
                                        from (select * from class_apply where CA_CANCEL = 'N') ca3 join review r  on r.REVIEW_CODE = ca3.CA_CODE
                                         group by ca3.class_code) ca4 on c4.class_code = ca4.class_code
    order by allavg desc
;
-- 위에꺼 적용 하면됨
select 
c.CLASS_code, c.CATEGORY_CODE, CLASS_INTRO, CLASS_CUR, CLASS_HOST, CLASS_ALLTIME, CLASS_PRD, AREA_CODE, CLASS_LEVEL, CLASS_MIN,CLASS_MAX,
c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
        order by (select C4.*, allavg
                        from class c4 left join (select ca3.CLASS_CODE, avg(r.REVIEW_GRADE) allavg
                                                            from (select * from class_apply where CA_CANCEL = 'N') ca3 join review r  on r.REVIEW_CODE = ca3.CA_CODE
                                                            group by ca3.class_code) ca4 on c4.class_code = ca4.class_code
                        where c4.class_code = c.class_code) desc, c.class_code desc
;
select allavg,
c.CLASS_code, c.CATEGORY_CODE, CLASS_INTRO, CLASS_CUR, CLASS_HOST, CLASS_ALLTIME, CLASS_PRD, AREA_CODE, CLASS_LEVEL, CLASS_MIN,CLASS_MAX,
c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from (select C4.*, allavg
                        from class c4 left join (select ca3.CLASS_CODE, avg(r.REVIEW_GRADE) allavg
                                                            from (select * from class_apply where CA_CANCEL = 'N') ca3 join review r  on r.REVIEW_CODE = ca3.CA_CODE
                                                            group by ca3.class_code) ca4 on c4.class_code = ca4.class_code
                        )  c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
    order by allavg desc, c.class_code desc
;
select 
NVL((select avg(r.REVIEW_GRADE) 
                                                            from (select * from class_apply where CA_CANCEL = 'N') ca3 join review r  on r.REVIEW_CODE = ca3.CA_CODE
                                                            WHERE C.class_code = ca3.class_code ), 0) allavg,
c.CLASS_code, c.CATEGORY_CODE, CLASS_INTRO, CLASS_CUR, CLASS_HOST, CLASS_ALLTIME, CLASS_PRD, AREA_CODE, CLASS_LEVEL, CLASS_MIN,CLASS_MAX,
c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS  c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
--        and class_code in(select class_code from class_schedule 
--                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
    order by allavg desc, c.class_code desc
;


select * from (select t1.*, rownum r 
                        from (select (select count(ca2.ca_code) from class_apply ca2 where ca2.CA_CANCEL = 'N' and ca2.CA_CODE in (select review_code from review where REVIEW_GROUP = 2) and ca2.class_code = c.class_code) groupcnt,
                                (select count(ca.ca_code)  from CLASS_apply ca where ca.ca_cancel = 'N' and ca.class_code = c.class_code) cacnt, ROUND(allavg,1) allavg, allcnt, c.class_code ,c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
                                    from class c left join (select ca.CLASS_CODE, avg(r.REVIEW_GRADE) allavg , count(r.review_GRADE) allcnt
                                    from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE 
                                    group by ca.class_code) ca on c.class_code = ca.class_code where 1=1
                                                                                                                        and area_code = 11 -- 지역 선택
                                                                                                                        and category_code = 3 -- 카테고리 선택
                                                                                                                        and class_level in (1,2,3) -- 난이도 선택
                                                                                                                        order by allavg desc
                                    ) t1 ) t2 where r between 1 and 9
;
select * from (select t1.*, rownum r 
                        from (select (select count(ca2.ca_code) from class_apply ca2 where ca2.CA_CANCEL = 'N' and ca2.CA_CODE in (select review_code from review where REVIEW_GROUP = 0) and ca2.class_code = c.class_code) groupcnt,
                                (select count(ca.ca_code)  from CLASS_apply ca where ca.ca_cancel = 'N' and ca.class_code = c.class_code) cacnt, 
                                ROUND(allavg,1) allavg, allcnt, c.class_code ,c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
                                    from class c left join (select ca.CLASS_CODE, avg(r.REVIEW_GRADE) allavg , count(r.review_GRADE) allcnt
                                    from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE 
                                    group by ca.class_code) ca on c.class_code = ca.class_code where 1=1
                                    ) t1 ) t2 where r between 1 and 40
;
select * from (select t1.*, rownum r 
                        from (select 
                                (select count(ca.ca_code)  from CLASS_apply ca where ca.ca_cancel = 'N' and ca.class_code = c.class_code) cacnt, ROUND(allavg,1) allavg, allcnt, c.class_code ,c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
                                    from class c left join (select ca.CLASS_CODE, avg(r.REVIEW_GRADE) allavg , count(r.review_GRADE) allcnt
                                    from (select * from class_apply where CA_CANCEL = 'N') ca join review r  on r.REVIEW_CODE = ca.CA_CODE 
                                    group by ca.class_code) ca on c.class_code = ca.class_code where 1=1
                                                                                                                        order by (select count(ca.class_code) cnt
                         from class c2 left OUTER join (select * from class_apply
                                                            where CA_CANCEL = 'N' and CA_CODE in 
                                                            (select review_code from review
                                                             where REVIEW_GROUP = 1)) ca on c2.class_code = ca.class_code
                         where c2.class_code = c.class_code
                         group by c2.class_code) desc
                                    ) t1 ) t2 where r between 1 and 40
;
-- 리뷰 동행추천 유형 개수에 따른 정렬
-- 1.동행 추천 유형에 따라 리뷰 조회
select review_code
    from review
    where REVIEW_GROUP = 1
;
-- 2.해당 리뷰를 가진 취소가 아닌 신청 내역의 클래스 코드
select class_code
    from class_apply
    where CA_CANCEL = 'N' and CA_CODE in (select review_code
                                     from review
                                     where REVIEW_GROUP = 1)
;
--3. 해당 신청 내역을 가진 class_code 순으로 클래스 정렬
select c2.class_code, count(ca.class_code) cnt
    from class c2 left join (select * from class_apply
                                        where CA_CANCEL = 'N' and CA_CODE in (select review_code
                                        from review
                                        where REVIEW_GROUP = 1)) ca on c2.class_code = ca.class_code
    group by c2.class_code
    order by cnt desc
;
-- 적용
select * --c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
        order by (select count(ca.class_code) cnt
                         from class c2 left OUTER join (select * from class_apply
                                                            where CA_CANCEL = 'N' and CA_CODE in 
                                                            (select review_code from review
                                                             where REVIEW_GROUP = 1)) ca on c2.class_code = ca.class_code
                         where c2.class_code = c.class_code
                         group by c2.class_code) desc, c.class_code desc
;
select * --c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADDRESS, c.CLASS_PRICE
    from CLASS c  --join CLASS_SCHEDULE cs using(class_code)
    where CLASS_NAME Like '% %' -- 키워드 검색
        and area_code = 11 -- 지역 선택
        and category_code = 3 -- 카테고리 선택
        and class_level in (1,2,3) -- 난이도 선택
        and class_code in(select class_code from class_schedule 
                                    where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
        and CLASS_PRICE between 0 and 9999999
        order by (select count(ca.class_code) cnt
                     from class c2 left join (select * from CLASS_apply where ca_cancel = 'N') ca on c2.CLASS_CODE = ca.class_code
                     where  c2.class_code = c.class_code
                     group by c2.class_code) desc, 
                     (select count(ca2.class_code) cnt
                         from class c3 left join (select * from class_apply
                                                            where CA_CANCEL = 'N' and CA_CODE in 
                                                            (select review_code from review
                                                             where REVIEW_GROUP = 1)) ca2 on c3.class_code = ca2.class_code
                         where c3.class_code = c.class_code
                         group by c3.class_code) desc, 
                         c.class_code desc
;

Insert into S2.CLASS (CLASS_CODE,CATEGORY_CODE,CLASS_NAME,CLASS_IMG,CLASS_INTRO,CLASS_CUR,CLASS_HOST,CLASS_ALLTIME,CLASS_PRD,AREA_CODE,CLASS_ADDRESS,CLASS_PRICE,CLASS_LEVEL,CLASS_MIN,CLASS_MAX) values (99999,1,'[서촌] 겨울, 크리스마스 타르트 만들기','/images/class/235380.jpg','< 공방 운영 안내사항 >%%● 성인을 위한 베이킹 체험입니다. 13세 이하는 별도 과정으로 문의 부탁 드립니다.%%● 레시피가 제공되는 수업입니다. (온라인/오프라인에서 레시피 및 만들기 과정은 게재 하실 수 없습니다.)%%%%%%안녕하세요.%%솜씨당 작가 ''공유카페'' 입니다.%%%%실내에서 만드는 따뜻한 구움과자,%%특별한 겨울을 위한 타르트를 만들어 보아요.%%%%고급 초콜릿으로 만든 가나슈와 딸기, 부드러운 크림치즈와 복숭아로 크리스마스 장식을 만들고,%%스노우를 솔솔 뿌려 느끼는 겨울 감성,%%담백한 에그타르트도 빠질 수 없죠^^%%%%베이킹 초보도 걱정하지 마세요!!!%%%%서촌의 하늘과 산 그리고 한옥의 아름다운 뷰를 보며 느끼는 베이킹!%%필요한 재료는 모두 제공되므로 준비물은 설레이는 마음!입니다.%%%%[크리스마스타르트 3종 만들기]%%- 에그/크림치즈/딸기크림, 에그/크림치즈/가나슈(초코), 에그/크림치즈/말차 상품 중 인별 1개 선택 가능 합니다.%%- 상품 선택시 9개/18개 중 개수를 선택하여 만들어 가실 수 있어요. (개수 선택 시 인원 수를 꼭 입력 해 주세요!!)%%- 겨울 타르트 만들기 과정은 추가 재료를 가지고 꾸미기에 집중하는 과정입니다. 데코가 필요 없으신 분은 일반 타르트 과정으로 신청 부탁 드립니다.%%- 겨울 타르트 데코 장식은 스프링클 3종과 장식 3개가 랜덤으로 제공됩니다.%%- 한정된 재료를 사용하여 재고 소진시까지 예약이 가능하며, 종류 변경은 불가능 합니다.%%- 모든 타르트 만들기에 생딸기가 포함되어요.%%%%* 완성 후 상자에 담아 드려요.%%%%--------------------------------------------%%★안내드립니다★%%%%[솜씨당] 취미생활 약속%%마스크는 필히 착용하시고, 손소독을 통해 안전한 취미생활을 즐기시기 바랍니다.%%','1단계 : 재료 소개 및 과정 소개하기%%신청한 종류에 따라 재료 및 만들기 과정을 소개합니다.%%2단계 : 타르트지 만들기%%개인별로 타르트지를 만들어 봅니다.%%3단계 : 타르트 필링 만들기%%신청한 타르트 종류에 따른 필링을 만들어요.%%- 에그 타르트, 구움 크림치즈 타르트 필링은 직접 만들어 봅니다.%%- 딸기 크림, 가나슈 크림, 말차 크림필링은는 사전에 만든 필링을 제공합니다.%% (데코에 시간을 집중하는 과정으로 진행 됩니다.)%%4단계 : 타르트 데코하기%%각자 원하는 모양으로 데코를 하여 만들어 봅니다.%%5단계 : 포장하기%%완성된 타르트를 전용 상자에 포장합니다.%%','오늘 뭐할까? %%고민하시는 분들을 위해 %%취미놀이 공간으로 초대합니다:)%%%%공유카페는 국내외에서 활동한 경력의 파티쉐와 경영전략 컨설턴트, 바리스타 등 다양한 업종의 전문가 강사진들과 함께 서비스를 기획, 제공하고 있습니다.%%%%최고 전문가들이 제공하는 %%비전문가의 재미를 위한 서비스,%%필요한 준비물은 오직 설레이는 마음!입니다.%%','총 1시간 30분','아늑한 공방에서 수업합니다.%%와이파이 이용이 가능합니다.%%커피/음료는 별도 구매하셔야 합니다.%%대중교통을 이용하시면 더 편합니다.%%',11,'서울 종로구 자하문로5길 18-13 (체부동)
1층 공유카페',63000,3,2,8);
rollback;
select * from class;
select * from class_schedule;
select * 
    from (select t1.*, rownum r from (select * 
                                            from class c 
                                            where CLASS_NAME Like '% %' -- 키워드 검색
                                                and area_code = 11 -- 지역 선택
                                                and category_code = 3 -- 카테고리 선택
                                                and class_level in (1,2,3) -- 난이도 선택
                                                and class_code in(select class_code from class_schedule 
                                                                            where bitand(CS_DAY,31) > 0 or bitand(CS_DAY,32) > 0 or bitand(CS_DAY,64) > 0) --평일이 있는지 확인은 31비교, 토요일은 32, 일요일은 64
                                                and CLASS_PRICE between 0 and 9999999
                                            order by (select count(ca.class_code) cnt
                                                                 from class c2 left join (select * from CLASS_apply where ca_cancel = 'N') ca on c2.CLASS_CODE = ca.class_code
                                                                 where  c2.class_code = c.class_code
                                                                 group by c2.class_code) desc, c.class_name desc ) t1 ) t2 
    where r between 1 and 9
;
select count(*) cnt from class where 1=1 and class_name LIKE '%크리스%' and area_code =22 and category_code =4 and class_price between 0 and 999999999;
--목록 정렬 - 가격
select c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADRESS, c.CLASS_PRICE
    from CLASS c join AREA a using(AREA_CODE) 
                join CATEGORY cg using(category_code)
                join CLASS_SCHEDULE cs using(class_code)
    where c.CLASS_NAME = '키워드'
        and a.area_name = '지역' 
        and cg.category_name = '카테고리' 
        and c.class_level = 1
        and cs.cs_day = 1
--    order by C.CLASS_PRICE desc -- 가격 높은 순
    order by C.CLASS_PRICE asc -- 가격 낮은 순
;




-- 동행유형 연인(3번)를 리뷰받은 클래스코드 리뷰 수 내림차순 조회
select cc.class_code
    from class_apply cc join review r on cc.ca_code = r.review_code
        where r.review_group = 3
        group by cc.class_code
        order by count(*) desc
;
-- 동행유형 연인(3번)를 리뷰받은 클래스정보 조회
select * 
    from class c
    where c.class_code in(select cc.class_code
    from class_apply cc join review r on cc.ca_code = r.review_code
        where r.review_group = 3
        group by cc.class_code)
;
--내 클래스 신청내역 목록 정보 조회- 옵션 있는 경우
select C.CLASS_NAME "클래스명", CA.CA_DATE "신청 날짜", CS.CS_STIME "시작시간", 
            CS.CS_FTIME "종료시간", CA.CA_TOTAL "신청 인원", CA.CA_TIME "신청접수 시간", 
            CO.CO_NAME "옵션명", (C.CLASS_PRICE+CO.CO_PRICE)*CA.CA_TOTAL "금액"
    from class_apply ca join CLASS_SCHEDULE cs using(cs_code,class_code)
                        join class c using(class_code)
                        join class_option co using(co_code)     
    where CA.MEMBER_ID = '아이디' 
            and CA.CA_CANCEL = 'N'
            and CA.CO_CODE is not null
;
--내 클래스 신청내역 목록 정보 조회- 옵션 없는 경우
select C.CLASS_NAME "클래스명", CA.CA_DATE "신청 날짜", CS.CS_STIME "시작시간", 
            CS.CS_FTIME "종료시간", CA.CA_TOTAL "신청 인원", CA.CA_TIME "신청접수 시간", 
            (C.CLASS_PRICE*CA.CA_TOTAL) "금액"
    from class_apply ca join CLASS_SCHEDULE cs using(cs_code,class_code)
                        join class c using(class_code)  
    where CA.MEMBER_ID = '아이디' 
            and CA.CA_CANCEL = 'N'
            and CA.CO_CODE is null
;
-- 내 클래스 신청내역에 리뷰 등록 여부 체크 - 1이면 리뷰 있음
select count(*)
    from CLASS_APPLY ca join REVIEW r on CA.CA_CODE = R.REVIEW_CODE
;

--내 클래스 취소내역 목록 정보 조회 - 옵션 있는경우
select C.CLASS_NAME "클래스명", CA.CA_DATE "신청 날짜", CS.CS_STIME "시작시간", 
            CS.CS_FTIME "종료시간", CA.CA_TOTAL "신청 인원", AC.AC_TIME "취소접수 시간", 
            CO.CO_NAME "옵션명", (C.CLASS_PRICE+CO.CO_PRICE)*CA.CA_TOTAL "금액"
    from (select * from class_apply where CA.MEMBER_ID = '아이디' and CA.CA_CANCEL = 'Y' and CA.CO_CODE is not null ) ca 
                        join CLASS_SCHEDULE cs using(cs_code,class_code)
                        join class c using(class_code)
                        join class_option co using(co_code)  
                        join APPLY_CANCEL ac on ca.ca_code = ac.ac_code
;
--내 클래스 취소내역 목록 정보 조회 - 옵션 없는 경우
select C.CLASS_NAME "클래스명", CA.CA_DATE "신청 날짜", CS.CS_STIME "시작시간", 
            CS.CS_FTIME "종료시간", CA.CA_TOTAL "신청 인원", AC.AC_TIME "취소접수 시간", 
            (C.CLASS_PRICE*CA.CA_TOTAL) "금액"
    from class_apply ca join CLASS_SCHEDULE cs using(cs_code,class_code)
                        join class c using(class_code)
                        join APPLY_CANCEL ac on ca.ca_code = ac.ac_code
    where CA.MEMBER_ID = '아이디'
            and CA.CA_CANCEL = 'Y'
            and CA.CO_CODE is null
;
--내 리뷰 목록 정보 조회
select C.CLASS_NAME "클래스명", R.REVIEW_TIME "등록시간", R.REVIEW_CONT "내용",
        R.REVIEW_GRADE "총평점", R.REVIEW_GROUP "추천 유형"
    from REVIEW r join CLASS_APPLY ca on R.REVIEW_CODE = CA.CA_CODE
                    join MEMBER m on M.MEMBER_ID = CA.MEMBER_ID
                    join CLASS c on CA.CLASS_CODE = C.CLASS_CODE
    where CA.MEMBER_ID = '아이디' and CA.CA_CANCEL = 'N'
;
-- 내 리뷰 목록 정보 조회-리뷰사진
select RP_ROUTE "이미지 경로"
    from REVIEW_PHOTO 
    where REVIEW_CODE = 1 --해당 review_code
;
-- 리뷰 수정 
update review 
    set REVIEW_TIME=CURRENT_TIMESTAMP, REVIEW_CONT=?, 
        REVIEW_GRADE=?, REVIEW_KIND=?, REVIEW_COMPONENT=?, 
        REVIEW_FACILITY=?, REVIEW_LEVEL=?, REVIEW_GROUP=? 
where review_code=?
;

--클래스 상세정보 조회
select *
    from class
    where class_code = 124
; -- 상세정보 해당 클래스의 사진 가져오기
select *
    from CLASS_PHOTO
    where class_code = 26851 and CP_TYPE = 0
; -- 사진
select *
    from CLASS_SCHEDULE
    where class_code = 124
; -- 해당 클래스 코드에 해당 날짜 및 요일을 표함하는 일정 조회
select class_code, CLASS_CODE, CS_STIME, CS_FTIME
    from CLASS_SCHEDULE
    where CLASS_CODE = 26851 and BITAND(cs_day, 4) = 4 and TO_DATE('2022-12-21', 'YYYY-MM-DD') between cs_sdate and cs_fdate
;
-- 해당 클래스의 옵션 가져오기
select CO_CODE, CO_NAME, CO_PRICE
    from class_option
    where class_code = 26851
;

-- 해당 클래스의 리뷰 가져오기
select r.*, m.MEMBER_NAME
    from review r join class_apply ca on r.REVIEW_CODE = ca.CA_CODE
                        join member m on ca.MEMBER_ID = m.member_id
    where REVIEW_CODE in (select CA_CODE
                                        from CLASS_APPLY 
                                        where class_code = 23950)
;
select * from review;
select * 
    from review 
    where REVIEW_CODE in (select CA_CODE 
                                    from CLASS_APPLY  
                                    where class_code = 23950)
;
select r.*, member_name
    from class_apply ca join REVIEW r on ca.CA_CODE = r.REVIEW_CODE 
                                join member m using(member_id)
    where class_code = 23950
;
select r.*, (select member_name from member where member_id = ca.member_id) member_name
    from class_apply ca join REVIEW r on ca.CA_CODE = r.REVIEW_CODE 
    where class_code = 23950
;
-- 리뷰코드에 리뷰이미지 가져오기
select RP_ROUTE "이미지 경로"
    from REVIEW_PHOTO 
    where REVIEW_CODE = 239501 --해당 review_code
;  
select RP_ROUTE "이미지 경로"
    from REVIEW_PHOTO 
    where REVIEW_CODE Like '23%' --해당 review_code
;  

-- 리뷰 구매시 클래스신청 넘버 마지막꺼 조회
select distinct last_value(CA_CODE)  over() a
    from class_apply
    where class_code = 23950
;
select *
    from class_apply
    where class_code = 23950
;



--마이페이지 내역 조회
-- 예시용 데이터 update 및 insert
update class_apply set CA_CANCEL = 'Y' where ca_code in (194773,200471,287991);
insert into apply_cancel(AC_CODE) values(194773);
insert into apply_cancel(AC_CODE) values(200471);
insert into apply_cancel(AC_CODE) values(287991);

delete from REVIEW where REVIEW_CODE in (194773,200471,287991);
delete from REVIEW_PHOTO where REVIEW_CODE in (194773,200471,287991);
commit;
--신청 내역
select *  
    from CLASS_APPLY
    where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'N'
;
select ca.CA_CODE,class_code,c.CLASS_NAME,c.CLASS_PRICE,ca.CA_TOTAL,ca.CA_DATE,ca.CA_TIME as TIME,ca.CO_CODE,cs.CS_STIME ,cs.CS_FTIME
    from (select * from CLASS_APPLY where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'N') ca 
                 join CLASS c using(class_code)
                 join CLASS_SCHEDULE cs using(class_code,cs_code)
--                 join CLASS_OPTION co using(class_code,co_code)
;
select ca.CA_CODE,class_code,c.CLASS_NAME,c.CLASS_PRICE,ca.CA_TOTAL,TO_CHAR(ca.CA_DATE,'YYYY-MM-DD') as CA_DATE,TO_CHAR(ca.CA_TIME,'YYYY-MM-DD HH24:MI:SS') as TIME,ca.CO_CODE,cs.CS_STIME ,cs.CS_FTIME
    from (select * from CLASS_APPLY where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'N') ca 
                 join CLASS c using(class_code)
                 join CLASS_SCHEDULE cs using(class_code,cs_code)
--                 join CLASS_OPTION co using(class_code,co_code)
;
--옵션이 없는 경우를 위해 옵션 번호에 따라 값 가져오기
select co_name,co_price
    from CLASS_OPTION
    where CO_CODE = 3 and CLASS_CODE = 19477
;
-- 해당 신청 내역에 작성된 리뷰가 있는지 여부
select count(review_code) as cnt
    from REVIEW
    where REVIEW_CODE = 273751
; 
select *
    from review
    where review_code =33753;
commit;
-- 취소 내역
select aca.CA_CODE,c.CLASS_NAME ,c.CLASS_PRICE,aca.CA_TOTAL,aca.CA_DATE,aca.AC_TIME as TIME,co.CO_NAME,co.CO_PRICE,cs.CS_STIME ,cs.CS_FTIME
    from (select ca.*,ac.AC_TIME
                from CLASS_APPLY ca join APPLY_CANCEL ac on ca.CA_CODE = ac.AC_CODE 
                where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'Y') aca 
                    join CLASS c using(class_code)
                    join CLASS_SCHEDULE cs using(class_code,cs_code)
                  --  join CLASS_OPTION co using(class_code,co_code)
;
select ca.CA_CODE,ca.CLASS_CODE ,ca.CA_TOTAL,ca.CA_DATE,ac.AC_TIME,ca.CO_CODE,ca.CS_CODE
    from CLASS_APPLY ca join APPLY_CANCEL ac on ca.CA_CODE = ac.AC_CODE
    where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'Y'
;
--리뷰 관리
select *
    from REVIEW
    where REVIEW_CODE in (select CA_CODE from CLASS_APPLY where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'N')
;
select  c.CLASS_NAME, r.REVIEW_CODE,r.REVIEW_COMPONENT,r.REVIEW_CONT,r.REVIEW_FACILITY,r.REVIEW_GRADE,r.REVIEW_GROUP,r.REVIEW_KIND,r.REVIEW_LEVEL,TO_CHAR(r.REVIEW_TIME,'YYYY-MM-DD HH24:MI:SS') as REVIEW_TIME
    from (select CA_CODE,CLASS_CODE 
                from CLASS_APPLY 
                where MEMBER_ID = 'user2@user.com' and CA_CANCEL = 'N') ca
                join REVIEW r on ca.CA_CODE = r.review_code
                join class  c using(class_code)
;
select * 
    from review_photo
;


--목록 페이지 조회
select * 
    from (select t1.*, rownum r from  (select * from class) t1 ) t2 
    where r between 1 and 10
;
select * from class;