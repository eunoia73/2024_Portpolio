# 2024_Portpolio
  
## 📄 프로젝트 목표
<hr>

웹사이트의 기본 기능을 개발하고 배포하는 경험을 쌓는 것을 목표로 한다.


- ERD 구성 

<img src="https://velog.velcdn.com/images/eunoia73/post/98980016-45ae-4779-a8a8-4e551d2a67e4/image.png" width="80%" height="50%">



## ⚙️ 기술스택
<hr>

Backend
`SpringFramework(5.3.32)` `Maven(3.9.6)` `MyBatis` `JUnit5`

Frontend
`HTML` `CSS` `JavaScript` `JQuery` `JSP` `Tiles`

Database
`MySQL(8.0)`

Deploy
`Apache Tomcat` `AWS(EC2, RDS)` `Squid Shell` `Nginx` `FileZilla`

Etc
`GitHub` `Sourctree` 

## 🛠️ 구현기능
<hr>

### 1.  **회원가입**

사용자가 새로운 계정을 만들기 위해 아이디, 이메일, 이름, 비밀번호를 입력하면 이를 검증하고 DB에 저장한다.
#### 1. 검증
-   **아이디** : 공백 불가능, 6~12자리 영어 또는 숫자만 가능, 중복체크를 해야한다.
-   **이메일** : 공백 불가능, ‘aaa@aaa.aaa'형식
-   **이름** : 공백 불가능, 한글 또는 영어만 가능하며 2~15자리
-   **비밀번호** : 8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용
<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/62330f80-9973-47c5-bb71-4d61dabfbc62/image.png" width="50%" height="50%"><img src="https://velog.velcdn.com/images/eunoia73/post/8eba99cc-565b-47a1-9a30-7d99e281ff55/image.png" width="50%" height="50%">
</div>


#### 2. 비밀번호 암호화
Bcrypt라이브러리 사용

#### 3. 이메일 인증
javax.mail 라이브러리 사용
주어진 폼에 맞게 입력후 '가입하기'버튼을 누르면 인증 이메일이 발송된다.

### 2.  **로그인**
#### 1. 검증
-   **아이디** : 공백 불가능, 6~12자리 영어 또는 숫자만 가능
-   **비밀번호** : 8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용

<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/3cdfb908-e841-4462-a0a1-bdc3d359a3f8/image.png" width="50%" height="50%">
<img src="https://velog.velcdn.com/images/eunoia73/post/0ca923fb-e715-4587-9287-6de3f84174b8/image.png" width="50%" height="50%">
</div>

#### 2. 아이디 저장
- cookie를 통해 아이디 저장 기능 구현

#### 3-1. 비밀번호 변경하기 (변경폼 보내기)
- 입력한 정보를 기반으로 db에 아이디가 존재하는지 확인한다.
- 아이디가 존재한다면, 입력한 이메일로 비밀번호를 변경할 수 있는 링크가 포함된 메일을 보낸다. 

<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/182f7a98-2329-4040-8c84-8fa0d1e6f2cc/image.png" width="50%" height="50%"><img src="https://velog.velcdn.com/images/eunoia73/post/08623c15-b28e-41f1-8900-195e2e1d0335/image.png" width="50%" height="50%">
</div>

#### 3-2. 비밀번호 변경하기 (변경하기)
- 사용자가 입력한 이메일로 비밀번호 변경 가능한 링크 보내기

<img src="https://velog.velcdn.com/images/eunoia73/post/34b98551-a2ff-456b-a95c-583b704fa327/image.png" width="50%" height="50%">

### 3.  **게시판**
 로그인을 한 사용자는 게시글을 쓰고, 읽고, 수정하고, 지우는 등 여러가지 작업을 할 수 있다. 또한 첨부파일을 다운로드받고, 게시글에 대해 좋아요/싫어요를 누르고, 댓글을 달 수도 있다. 
 
#### 1. 게시판 조회
- 번호, 제목, 날짜, 작성자, 첨부파일 개수, 댓글 개수 조회 가능 
- 페이징 기능을 통해 게시물 리스트 확인 가능

<img src="https://velog.velcdn.com/images/eunoia73/post/f5d2c243-9e7b-4047-a1c0-74fb7bdee402/image.png" width="100%" height="50%">


#### 2. 게시글 입력 (C)
- trumbowyg 에디터를 통해 게시글 내용 작성 가능
- 파일 첨부 3개까지 가능

<img src="https://velog.velcdn.com/images/eunoia73/post/24c05d2f-000b-4cc7-8156-75025ff23781/image.png" width="100%" height="50%">


#### 3. 게시글 조회 (R)

- 파일 다운로드 가능
- 게시글 좋아요/싫어요 가능
- 댓글 작성 가능

<img src="https://velog.velcdn.com/images/eunoia73/post/9b150c77-d8dd-4fae-8c28-fd797e4c4857/image.png" width="100%" height="50%">

##### 3-1. 댓글 작성 및 수정
- 본인이 작성한 댓글에 대해 수정, 삭제 가능

<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/53736f22-1ef2-41fc-ac74-b8e2583c8261/image.png" width="50%" height="50%"><img src="https://velog.velcdn.com/images/eunoia73/post/6939d7e6-aa92-490f-b4ac-a313302ffb9f/image.png" width="50%" height="50%">
</div>

<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/275f8a40-fb72-4d65-a638-27c482ed318e/image.png" width="50%" height="50%"><img src="https://velog.velcdn.com/images/eunoia73/post/598018bc-99ad-452b-ab96-ce526df7aa17/image.png" width="50%" height="50%">
</div>


#### 4. 게시글 수정 (U)
- 본인이 작성한 게시물에 대해 수정, 삭제 가능

<img src="https://velog.velcdn.com/images/eunoia73/post/d0a2ec4f-6919-4e57-a3d0-b4ed1483b47c/image.png" width="100%" height="50%">


#### 5. 게시글 삭제 (D)
- 본인이 작성한 게시물에 대해 수정, 삭제 가능
- 게시글 삭제 시 첨부파일, 댓글, 좋아요/싫어요 모두 삭제되고 나서 게시글 삭제됨

<img src="https://velog.velcdn.com/images/eunoia73/post/7bcd5d04-1961-4047-b83e-7b8aeff32d6b/image.png" width="100%" height="50%">

<img src="https://velog.velcdn.com/images/eunoia73/post/9aa7ec20-937f-431d-ba69-6537b342c29c/image.png" width="100%" height="50%">



### 4.  **Contact 페이지**
- 회사명과 이메일을 형식에 맞게 입력하면 이력서를 보내는 기능 

<div style="display: flex; justify-content: center;">
<img src="https://velog.velcdn.com/images/eunoia73/post/8618cc53-c6ad-4170-a354-e3600adef05a/image.png" width="50%" height="50%">
<img src="https://velog.velcdn.com/images/eunoia73/post/a9f49be1-80bf-4296-8c34-e1ed8d0d9d6f/image.png" width="50%" height="50%">
</div>

