--시도코드 엑셀파일로 임포트 완료--
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (11.0, '서울');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (21.0, '부산');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (22.0, '대구');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (23.0, '인천');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (24.0, '광주');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (25.0, '대전');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (26.0, '울산');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (29.0, '세종');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (31.0, '경기도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (32.0, '강원도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (33.0, '충청북도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (34.0, '충청남도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (35.0, '전라북도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (36.0, '전라남도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (37.0, '경상북도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (38.0, '경상남도');
INSERT INTO AREA (AREA_CODE, AREA_NAME) VALUES (39.0, '제주도');
commit;
--SELECT * FROM AREA;
--SELECT * FROM AREA WHERE AREA_NAME LIKE '%경기%';

-- 카테고리 데이터 insert
drop sequence class_sequence;
create SEQUENCE class_sequence start with 1 INCREMENT BY 1;
insert into category values(class_sequence.nextval ,'요리');
insert into category values(class_sequence.nextval ,'수공예');
insert into category values(class_sequence.nextval ,'플라워');
insert into category values(class_sequence.nextval ,'미술');
insert into category values(class_sequence.nextval ,'음악');
insert into category values(class_sequence.nextval ,'액티비티');
insert into category values(class_sequence.nextval ,'뷰티');
insert into category values(class_sequence.nextval ,'라이프스타일');
commit;
--select * from category;

--회원 데이터 insert
insert all
    into MEMBER values('user1@user.com','user0203!','배인숙','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user2@user.com','user0203!','류유현','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user3@user.com','user0203!','신인숙','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user4@user.com','user0203!','강남지','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user5@user.com','user0203!','오미르','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user6@user.com','user0203!','탁가을','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user7@user.com','user0203!','윤노을','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user8@user.com','user0203!','최나래','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user9@user.com','user0203!','김노웅','010-1234-5678','./images/member/basic.jpg')
    into MEMBER values('user10@user.com','user0203!','김시언','010-1234-5678','./images/member/basic.jpg')
    select * from dual;
commit;
    
--select * from member;