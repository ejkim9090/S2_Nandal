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
select c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADRESS, c.CLASS_PRICE
    from CLASS c join AREA a using(AREA_CODE) 
                join CATEGORY cg using(category_code)
                join CLASS_SCHEDULE cs using(class_code)
    where c.CLASS_NAME = '키워드'
        and a.area_name = '지역' 
        and cg.category_name = '카테고리' 
        and c.class_level = 1
        and cs.cs_day = 1
;
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

--목록 정렬 - 인기순,,? 맞을지,, 
select c.CLASS_IMG, c.CLASS_NAME, c.CLASS_ADRESS, c.CLASS_PRICE
    from CLASS c join AREA a using(AREA_CODE) 
                join CATEGORY cg using(category_code)
                join CLASS_SCHEDULE cs on c.class_code = cs.class_code
    where c.CLASS_NAME = '키워드'
        and a.area_name = '지역' 
        and cg.category_name = '카테고리' 
        and c.class_level = 1
        and cs.cs_day = 1
    order by (select count(ca.class_code) 
                from class_apply ca 
                where CA.CLASS_CODE = C.CLASS_CODE) desc
;
--목록 정렬 - 동행추천 유형-TODO




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
