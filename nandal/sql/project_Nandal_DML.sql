--시도코드 엑셀파일로 임포트 완료--
DESC AREA;
SELECT * FROM AREA;
SELECT * FROM AREA WHERE AREA_NAME LIKE '%경기%';

-- 카테고리 데이터 insert
desc category;
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
select * from category;

