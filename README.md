# README #

과제 개발 진행사항에 대한 기록을 하는 문서입니다.<br>
본 문서는 나정일의 작업 과정에 대한 예시입니다.


### 실행 가이드 - TO DO
1. 사전 설치 필요 <br> OpenJDK 1.8 버전, Intellij IDEA (Community), Git
2. 소스 받아오기<br> git clone https://github.com/imbolic0301/befamily_assignment.git
3. 프로젝트 경로로 이동 ex) cd demo <br>
4. 프로젝트 경로에서 gradle 빌드 <br> ./gradlew build
5. 실행 <br> java -jar ./build/libs/demo-0.0.1-SNAPSHOT.jar &
6. (선택) POSTMAN 을 이용해 API 호출 테스트

### 테스트 환경
POSTMAN 을 이용해서 테스트했습니다.  
프로젝트 내의 demo_postman_collection.json 을 import 하면 편리하게 사용 가능합니다.

### API 목록 - TO DO

+ 회원 관리
  + 회원 가입
  + 이메일 로그인
  + 핸드폰 로그인
  + 비밀번호 변경
  + 회원 본인 정보 조회
  
### 개발 작업 List

- [x] 사용할 RDB : H2 (InMemory, 바로 실행 가능)
- [x] 회원 테이블 정의
  - [x] 회원 정보 정의 : 이메일, 핸드폰, 주민번호, 나이, 비밀번호
  - [x] 공통 정보 정의 : 생성시간, 수정시간
  - [x] 로그인 관련 정보 정의 : access token 문자열, 마지막 로그인 시간(토큰 생명주기에 활용)
- [x] JWT 토큰 적용
  - [x] JWT 라이브러리 결정 필요 : JJWT 로 결정
- [x] 회원 정보 암호화 : 복호화 가능 / 불가능 분리
  - [x] 복호화 불가능한 정보 : 비밀번호
  - [x] 복호화 가능한 정보 : 이메일, 핸드폰, 주민번호, 나이
- [x] 실행 가능 소스 상태로 만들기
- [ ] ~~(선택) Docker 환경에서 배포 및 실행 가능하게 만들기~~

### 작업 기록
나정일  
<br>
회원 기능 관련 초안 파일 추가 완료
<br>
JPA Entity 의 데이터 생성 및 변경 시간 필드 추가 완료
<br>
비밀번호 변경 기능 관련 초안 파일 추가 완료  
회원 본인 정보 조회 기능 관련 초안 파일 추가 완료  
세션 기능 작업 완료
암호화 적용 작업 완료  
예외 핸들러 적용 완료



