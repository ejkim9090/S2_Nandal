# 세미 프로젝트 - 난달 원데이 클래스

# Contents 📖
1. [개발 배경과 목표](#1️⃣-개발-배경과-목표)
2. [개발 환경](#2️⃣-개발-환경)
3. [주요 기능](#3️⃣-주요-기능)
4. [산출물](#4️⃣-산출물)
5. [최종발표자료(Google Drive)](https://drive.google.com/file/d/1gRAI_0MbRt3lTkd1YDYI3rnQ9ZZ93Y5Q/view?usp=sharing)

## 1️⃣ 개발 배경과 목표
### 개발 배경
- 일상에서 취미 생활을 가지고 싶지만 시간에 쫓겨 긴 시간과 노력을 쏟는 데 어려움을 겪는 대학생, 직장인들이 많다.
- 원데이 클래스는 시간, 비용에 따라 부담 없이 짧은 시간내에 다양한 라이프스타일을 경험 할 수 있다.
- 코로나로 인한 외부 활동의 제한이 많이 풀리면서 스트레스   해소, 새로운 취미 활동 찾기 등의 이유로 원데이클래스 플랫폼을 찾는 소비자가 많아졌다.

### 개발 목표

- 편의성
  - 손쉽게 사용하기 위해 직관적인 UI 제공한다.
  - 리뷰 작성 시 추상적인 평가에 대해 선택지와 동행 유형에 따른 추천 선택 제시한다.
- 접근성
  - 클래스 조회 시 리뷰 추천 정보를 기반으로 정렬을 제공하여 원하는 유형의 클래스를 선별하기 용이하다.

## 2️⃣ 개발 환경
<p align="center"><img src="https://user-images.githubusercontent.com/116356234/227253152-a512e104-b310-4f1f-a2eb-65d74890e08a.png"></p>

## 3️⃣ 주요 기능

<details>
<summary><h3>✅ 회원가입/로그인 </h3></summary>
<div markdown="1">
  
<br>
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
회원가입
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![회원가입 800](https://user-images.githubusercontent.com/116356234/228622134-e5a7db10-c7fb-4037-8dd8-09f95b2561e1.gif) 
  
</div>

- 정보입력 시 문구 안내
  - 비밀번호 입력 시 "최소 8자 이상, 영문,숫자,특수문자(!@#$%) 각 1개 이상" 형식 확인 안내
  - 비밀번호 확인 입력 시 비밀번호와 일치 확인 한내
  - 휴대폰 번호 입력 시 형식 확인 안내
- 인증번호 받기 버튼 클릭 시 결과에 따라 문구 안내 및 이메일로 인증 메일 발송
- 인증번호 확인 버튼 클릭 시 결과 alert
- 회원가입 버튼 클릭 시 유효성 검사에 따른 alert
  - 아이디 인증 확인
  - 비밀번호 "최소 8자 이상, 영문,숫자,특수문자(!@#$%) 각 1개 이상" 형식 확인
  - 휴대폰 번호 형식 확인
- 회원가입 성공 시 로그인 페이지로 이동
 
</div>

---
<br> 
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
로그인
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![로그인 800](https://user-images.githubusercontent.com/116356234/228622051-92735add-ef39-46b5-a0e2-7825009342a2.gif)   
  
</div>

- 로그인 시 아이디/비밀번호가 틀릴경우 alert
- 로그인 성공 시 메인페이지로 이동

</div>
</details>

<details>
<summary><h3>✅ 메인 페이지 </h3></summary>
<div markdown="1">
  
<br>
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
클래스/리뷰 추천 
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![메인 페이지 800](https://user-images.githubusercontent.com/116356234/228622068-f6d55f2d-945c-40ba-a28e-fdd0e412b466.gif)
  
</div>

- 추천 유형에 따라 클래스 목록 노출
- 좌우 버튼 클릭 시 목록 이동
  - 클래스 개수에 따라 이동 횟수 설정
  - 각 방향으로 마지막일 경우 해당 방향 버튼 숨김
- 클래스, 리뷰 클릭 시 상세 페이지로 이동
- 카테고리 클릭 시 해당 클래스 목록 조회 페이지로 이동

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
목록 출력 및 목록이동 설정
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

![메인 페이지](https://user-images.githubusercontent.com/116356234/229087535-5436495f-207d-4692-b098-3807ab3b021d.png)
 
</div>
</details>

<details>
<summary><h3>✅ 목록 페이지 </h3></summary>
<div markdown="1">
  
<br>
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
클래스 목록 조회/정렬
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![목록 조회 800](https://user-images.githubusercontent.com/116356234/228622085-b3357a84-e936-4fd9-ab2d-9315aef348c5.gif) 
  
</div>

- 다른 페이지에서 카테고리 선택 및 검색으로 진입 시 해당 값 적용 
- 페이지 진입/검색버튼 클릭/정렬 기준 선택/페이지 버튼 클릭 시 Ajax로 입력 및 선택 된 키워드, 조건, 정렬기준의 클래스 조회
- 총 클래스 개수 표시 및 9개의 클래스 마다 페이징 처리

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
목록 페이지 진입 시
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

![목록-데이터 처리](https://user-images.githubusercontent.com/116356234/229090712-8e506c7e-f9da-4dea-afb7-8e915e5a15dc.png)

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
조회 시 SQL문
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

![목록-SQL문](https://user-images.githubusercontent.com/116356234/229090720-728f2e8a-d51d-4bb1-b646-691c088eba17.png) 

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
조회 시 정렬 SQL문
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

![목록-정렬 기준 SQL문](https://user-images.githubusercontent.com/116356234/229090723-b8757799-df65-4679-a639-29cf3d98c41f.png) 

</div>
</details>

<details>
<summary><h3>✅ 상세 페이지 </h3></summary>
<div markdown="1">
  
<br>
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
클래스 상세 조회
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![상세 페이지 800](https://user-images.githubusercontent.com/116356234/228622097-3c8ac5a5-9e57-4add-aff6-b35f5db9b7ef.gif)  
  
</div>

- 메인/목록 페이지의 목록 클릭 및 마이페이지의 클래스명 클릭 시 상세페이지로 이동
- 사진 목록 클릭 시 해당 사진으로 확대보기 변경
- JQuery UI 라이브러리의 datepicker를 사용하여 클래스 신청 시 날짜 선택
- 네비의 각 부분 클릭 시 해당 부분으로 스크롤 이동 및 일정 이상 내려갈 경우 클래스 신청 버튼 표시
- 클래스 신청 버튼 클릭 시 신청 일정 선택 UI 노출 및 기존 정보 유지
- Kakao Maps API 사용하여 지도에 위치 안내
- 후기 별점 우측 ▼버튼 호버 시 드롭다운 상세 점수 표시 

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
상세 페이지 진입 시
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

![상세 페이지 이동](https://user-images.githubusercontent.com/116356234/229093533-93e216ce-108f-4325-b68d-7f263a0c3d4d.png)
 
</div>

---
<br> 
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
클래스 신청
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![클래스 신청 800](https://user-images.githubusercontent.com/116356234/228622119-950696fa-cf43-4e77-9549-88f101ce71cc.gif)  
  
</div>

- JQuery UI 라이브러리의 datepicker를 사용하여 클래스 신청 시 날짜 선택
- 선택 한 날짜에 따라 Ajax를 통해 해당 날짜의 일정 목록 표시
- 신청 시 비로그인 시 alert 및 로그인 페이지로 이동
- 일정/인원 미선택 시 alert
- 신청 시 Ajax를 통해 신청 정보 추가 후 alert 및 페이지 재로드
![상세-클래스 신청](https://user-images.githubusercontent.com/116356234/229094696-719c94b1-d25d-49bc-a7f7-36d392c23dce.png)

<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
날짜 별 일정조회
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div>

<div align="center"><strong>JSP</strong></div>

![상세-날짜 별 일정조회](https://user-images.githubusercontent.com/116356234/229094687-36feb70d-2f61-46d8-a65d-26aaccd17222.png)  

<div align="center"><strong>SQL</strong></div>

![상세-날짜 별 일정조회(SQL)](https://user-images.githubusercontent.com/116356234/229094694-2dc9eaab-f21b-4ec0-9103-7fc5cc916ecb.png) 

</div>
</details>

<details>
<summary><h3>✅ 마이 페이지 </h3></summary>
<div markdown="1">
  
<br>
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
프로필 수정
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![프로필 수정 800](https://user-images.githubusercontent.com/116356234/228622127-f5e470d7-0caf-4c9f-97d3-ce82f6c2fe67.gif) 
  
</div>

- 내용
 
</div>

---
<br> 
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
신청 취소
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![신청 취소 800](https://user-images.githubusercontent.com/116356234/228622111-9cd6b5f8-52ea-4263-982e-865a67586f19.gif)   
  
</div>

- 내용

</div>

---
<br> 
  
<div align="center">
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
리뷰 등록/수정/삭제
<img src="https://res.cloudinary.com/dnik5jlzd/image/upload/c_scale,h_10,w_225/v1679302400/readme/%EC%84%A0_vkktmh.png">
</div><br>
<div align="center">
  
![리뷰 등록및수정 800](https://user-images.githubusercontent.com/116356234/228622056-43887fb3-805f-4386-8ba5-83020757c558.gif)   
  
</div>

- 내용

</div>
</details>


## 4️⃣ 산출물
## 1.개발일정
![일정](https://user-images.githubusercontent.com/116356234/228160064-d3644862-3945-4d58-93d0-4baba962796b.png)
[데일리 일정 보기(Google Drive)](https://docs.google.com/spreadsheets/d/1CawW7X56g70JCO-MmkeiacJanTxpTwznG2hZXZjfstY/edit?usp=sharing)

## 2.요구사항 정의서

<h3 align="center">마이페이지</h3>

![요구사항정의서(마이 페이지)](https://user-images.githubusercontent.com/116356234/228162759-a9741668-59dd-42b8-80c5-34508abe8c67.png)
[요구사항 정의서 전체보기(Google Drive)](https://docs.google.com/spreadsheets/d/1WZ8FKLGcB3sAmk1_qZcC9mDx_BMDflsnClbkX-mwUqw/edit?usp=sharing)

## 3. UI 설계서

<h3 align="center">상세 페이지(일부)</h3>

![project_Nandal  상세페이지](https://user-images.githubusercontent.com/116356234/228165166-86fb1eb1-795f-42bf-bceb-38424b011d97.png)
[UI 설계서 전체보기(Google Drive)](https://drive.google.com/file/d/1-nIWiOTHrDdHr_S33RS3rCn7dQmzimEM/view?usp=sharing)

## 4. 유스케이스 다이어그램
![이미지](https://user-images.githubusercontent.com/116356234/228153613-7a696ca3-6766-4445-b1c7-d83a39c59fc6.png)

## 5.시퀀스 다이어그램

<h3 align="center">회원가입</h3>

![시퀀스-회원가입 기능](https://user-images.githubusercontent.com/116356234/228153950-f66c6e08-c899-4102-9359-04d46a9289a0.jpg)

<hr>
<h3 align="center">클래스 신청</h3>

![클래스 신청](https://user-images.githubusercontent.com/116356234/228153973-5e9519d3-fe60-4464-be8b-1c9889627221.png)

<hr>
<h3 align="center">리뷰 등록</h3>

![리뷰등록](https://user-images.githubusercontent.com/116356234/228153988-b4ebeb30-f361-430c-a2d3-6b50b20deace.png)

<hr>
<h3 align="center">리뷰 수정</h3>

![리뷰 수정(시퀀스)](https://user-images.githubusercontent.com/116356234/228153997-9caf0c0a-5645-48c8-8ef3-24c2b7e911b1.png)

## 6.ERD 다이어그램
![ERD확대](https://user-images.githubusercontent.com/116356234/228155278-e4e019b3-54fd-4525-8756-3f4032c98902.png)

## 7.클래스 URL 맵핑
![url mapping](https://user-images.githubusercontent.com/116356234/228155963-c840fb30-9642-44d9-bfbd-395d42d704c0.png)

## 8.클래스 다이어그램

<h3 align="center">목록 조회</h3>

![class](https://user-images.githubusercontent.com/116356234/228155785-cd863372-4c76-4d13-8124-7d88f96b7d2e.png)

## 9.history
![히스토리 일부](https://user-images.githubusercontent.com/116356234/228162768-44f63460-d3c0-4712-9e2e-95bffab03af3.png)
[history 전체 보기(Google Drive)](https://docs.google.com/spreadsheets/d/17JAPmGYTPVRHDcrvCwwMwiZ-16RYISPayvNI8cIO3gg/edit?usp=sharing)


## 10.테스트 케이스

<h3 align="center">마이페이지</h3>

![테스트 케이스(마이 페이지)](https://user-images.githubusercontent.com/116356234/228162764-1d7083af-cd17-468f-8dc9-901ce7865f4b.png)
[테스트 케이스 전체 보기(Google Drive)](https://docs.google.com/spreadsheets/d/1vm2aPEtXIhMBIqChLwXK-q4RTqgk3pphlwWo8_pCQuY/edit?usp=sharing)

