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

-- 저장된 데이터 확인용
select * from CATEGORY; 
select * from area;  
select * from CLASS_SCHEDULE;
select * from CLASS_OPTION;
select * from CLASS_APPLY; 
select * from APPLY_CANCEL;
select * from REVIEW;
select * from MEMBER; -- 이미지 경로 수정
select * from CLASS; -- 이미지 경로 수정
select * from CLASS_PHOTO; -- 이미지 경로 수정
select * from REVIEW_PHOTO; -- 이미지 경로 수정

UPDATE CLASS SET CLASS_IMG = TRIM('.' FROM CLASS_IMG);
UPDATE MEMBER SET MEMBER_IMG = TRIM('.' FROM MEMBER_IMG);
UPDATE CLASS_PHOTO SET CP_ROUTE = TRIM('.' FROM CP_ROUTE);
UPDATE REVIEW_PHOTO SET RP_ROUTE = TRIM('.' FROM RP_ROUTE);
commit;
--update class set CATEGORY_CODE = 7 where class_code = 27339;
--commit;
SELECT TRIM('.' FROM CLASS_IMG) FROM CLASS;