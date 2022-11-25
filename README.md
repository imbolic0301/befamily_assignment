# README #

과제 개발 진행사항에 대한 기록을 하는 문서입니다.<br>
본 문서는 나정일의 작업 과정에 대한 예시입니다.

### 작업 기록
나정일 : 
<br>
회원 기능 관련 초안 파일 추가 완료
<br>
JPA Entity 의 데이터 생성 및 변경 시간 필드 추가 완료


작업 예정 순서

1. JPA 를 통해 인메모리 DB 를 대상으로 API 기능 구현
2. 암호화 구현
3. JWT 토큰을 활용한 세션 추가

### 실행 가이드 - TO DO
<br>

### API 목록 - TO DO

+ 회원 관리
  + 회원 가입
  + 이메일 로그인
  + 핸드폰 로그인
  + 비밀번호 변경
  + 회원 본인 정보 조회
  
### 개발 작업 List

- [ ] 사용할 RDB : H2 (InMemory, 바로 실행 가능)
- [ ] 회원 테이블 정의
  - [ ] 회원 정보 정의 : 이메일, 핸드폰, 주민번호, 나이, 비밀번호
- [ ] JWT 토큰 적용
  - [ ] JWT 라이브러리 결정 필요
- [ ] 회원 정보 암호화 : 복호화 가능 / 불가능 분리
  - [ ] 복호화 불가능한 정보 : 비밀번호
  - [ ] 복호화 가능한 정보 : 이메일, 핸드폰, 주민번호, 나이
- [ ] 실행 가능 소스 상태로 만들기
- [ ] (선택) Docker 환경에서 배포 및 실행 가능하게 만들기
  