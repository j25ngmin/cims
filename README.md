# 고객 문의 접수 및 답변 기능 개발
 
### 개요
고객 문의를 접수하고 상담사가 답변을 할 수 있는 프론트엔드 및 API 애플리케이션을 작성하는 과제입니다.


### 프로젝트 설명
* 환경 구성
1. Spring Boot 2.5.3
2. Java 11
3. DB h2
4. JPA, Spring Security, JWT
4. 사용 툴 : STS, Chrome

* 소스 구조
|-- README.md
`-- src/main/java
    |-- api
    |   |-- inquiry
    |   |   |-- controller
	|   |   |-- service
	|	|   |-- repository
	|	|   |-- dto
	|	|   |-- entity
	|   |   `-- mapper
    |   |-- user
    |       |-- controller
	|       |-- service
	|		|-- repository
	|		|-- dto
	|		|-- entity
	|       `-- mapper
    |-- core
        |-- authentication
        |-- config
        |-- error
        |-- handler
        |-- security
        `-- util
    
* 테이블 명세
INQUIRY 테이블
| 필드            | 설명           | 기타                                               |
|-----------------|----------------|----------------------------------------------------|
| INQUIRY_SEQ     | 문의 순번      |                                                    |
| INQUIRY_USER_ID | 문의 사용자 ID |                                                    |
| TITLE           | 제목           |                                                    |
| STATUS          | 상태           | INQ001 (001: 등록, 002: 담당자지정, 003: 답변완료) |
| INQUIRY_CONTENT | 문의 내용      |                                                    |
| INQUIRY_DATE    | 문의 일시      |                                                    |
| ANSWER_USER_ID  | 답변 사용자 ID |                                                    |
| ANSWER_CONTENT  | 답변 내용      |                                                    |
| ANSWER_DATE     | 답변 일시      |                                                    |
| CREATE_USER_ID  | 생성 사용자 ID |                                                    |
| CREATE_DATE     | 생성 일시      |                                                    |
| MODIFY_USER_ID  | 수정 사용자 ID |                                                    |
| MODIFY_DATE     | 수정 일시      |                                                    |


USER 테이블
| 필드           | 설명           | 기타                                         |
|----------------|----------------|----------------------------------------------|
| USER_ID        | 사용자 ID      |                                              |
| USER_NAME      | 사용자 이름    |                                              |
| PASSWORD       | 비밀번호       |                                              |
| USER_TYPE      | 사용자 유형    | USR001 (001: 일반, 002: 상담사, 003: 관리자) |
| CREATE_USER_ID | 생성 사용자 ID |                                              |
| CREATE_DATE    | 생성 일시      |                                              |
| MODIFY_USER_ID | 수정 사용자 ID |                                              |
| MODIFY_DATE    | 수정 일시      |                                              |


### 실행 방법
1. 사용자 목록 조회
	* 호출 URL : GET localhost:9090/api/v1/users
	* 파라미터 : 검색 키워드 선택 입력. UserDto에서 사용하는 복수개의 컬럼 가능
	      (Ex. ?search=userName:프로도 / ?search=userName:프로도 OR userName:*콘*)
	* 리턴 값 : 사용자 목록, 페이징 정보 리턴		
2. 사용자 단건 조회
	* 호출 URL : GET localhost:9090/api/v1/users/{userSeq}
	* 파라미터 : 사용자 시퀀스 필수값
	* 리턴 : 사용자 정보, 존재하지 않는 경우 NO_CONTENT(204) 발생 
3. 문의 목록 조회
	* 호출 URL : GET localhost:9090/api/v1/inquiries
	* 파라미터 : 검색 키워드 선택 입력. InquiryDto에서 사용하는 복수개의 컬럼 가능
	      (Ex. ?search=inquirySeq:1 / ?search=inquiryContent:문의 OR inquiryContent:*면접*)
	* 리턴 값 : 문의 목록, 페이징 정보 리턴
4. 문의 단건 조회
	* 호출 URL : GET localhost:9090/api/v1/inquiries/{inquirySeq}
	* 파라미터 : 문의 시퀀스 필수값
	* 리턴 : 문의 정보, 존재하지 않는 경우 NO_CONTENT(204) 발생 	
5. 문의 등록
	* 호출 URL : POST localhost:9090/api/v1/inquiries
	* 파라미터 : 문의 정보 필수값
	* 리턴 값 : 등록한 문의정보, 성공 시 OK(200) 발생
6. 문의 접수
	* 호출 URL : PUT localhost:9090/api/v1/accept-inquiries
	* 파라미터 : 문의 시퀀스, 문의 정보 필수값
	* 리턴 값 : 접수한 문의정보, 성공 시 OK(200) 발생
7. 문의 수정
	* 호출 URL : PUT localhost:9090/api/v1/inquiries
	* 파라미터 : 문의 시퀀스, 문의 정보 필수값
	* 리턴 값 : 수정한 문의정보, 성공 시 OK(200) 발생

### 문제 해결 전략
- 요구사항에 부합하며 간단한 DB 구조설계
- 구조화된 API 설계로 직관적인 프로그래밍
- JPA 사용을 통해 수월한 도메인 관리 가능
- JWT, Spring Security 개념 적용을 통해 backend 안정화

