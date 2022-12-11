--timestamp 확인용
drop table "test1";
CREATE TABLE "test1" (
	"CA_CODE"	NUMBER		NOT NULL,
	"REVIEW_TIME"	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP	NOT NULL
);
insert into "test1" values(2, CURRENT_TIMESTAMP);
select * from "test1";


--크롤링 class 테이블 데이터 insert 확인
select * from class;
select * from CLASS_PHOTO;
DESC CLASS;

delete from class_photo where class_code = 32010;
delete from class_photo where class_code = 23538;
delete from class where class_code = 32010;
delete from class where class_code = 23538;
commit;


--각 테이블 기본 vo생성용 컬럼확인
--완료
desc CATEGORY; 
desc CLASS;
desc CLASS_PHOTO; 
desc area; 
desc CLASS_SCHEDULE;
desc CLASS_OPTION;
desc MEMBER;
desc CLASS_APPLY;
desc APPLY_CANCEL;
desc REVIEW;
desc REVIEW_PHOTO;

--메인,목록 페이지의 클래스 목록 표시 정보
select CLASS_IMG, CLASS_NAME, AREA_NAME, CLASS_PRICE
    from CLASS join AREA using(AREA_CODE)
    where CLASS_NAME = '키워드' or CLASS_INTRO = '키워드';
--카테고리 선택 시 클래스 정보
select CLASS_IMG, CLASS_NAME, AREA_NAME, CLASS_PRICE
    from CLASS join AREA using(AREA_CODE) join CATEGORY using(category_code)
    where category_name = '카테고리';
--키워드 검색 시 
select CLASS_IMG, CLASS_NAME, AREA_NAME, CLASS_PRICE
    from CLASS join AREA using(AREA_CODE)
    where CLASS_NAME = '키워드';
--유형 선택 시
select CLASS_IMG, CLASS_NAME, AREA_NAME, CLASS_PRICE
    from CLASS join AREA using(AREA_CODE) 
                join CATEGORY using(category_code)
                join CLASS_OPTION using(class_code)
    where area_name = '지역' 
        and category_name = '카테고리' 
        and class_level = '1'
        and cs_day = 1
;
--
