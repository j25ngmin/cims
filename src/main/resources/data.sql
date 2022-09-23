DROP TABLE IF EXISTS USER; -- 사용자
DROP TABLE IF EXISTS INQUIRY; -- 문의

/*
DROP TABLE IF EXISTS ROLE; -- 권한
DROP TABLE IF EXISTS USER_ROLE_MAPPING; -- 사용자 권한 매핑
DROP TABLE IF EXISTS COMMON_CODE; -- 공통코드
*/


/*
CREATE TABLE ROLE (
    ROLE_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL-- 롤 시퀀스
    ROLE_NAME VARCHAR(50), -- 권한 이름
    ROLE_DISCRIPTION VARCHAR(300), -- 권한 설명
    CREATE_USER_ID VARCHAR(50) NOT NULL, -- 생성 사용자 ID
    CREATE_DATE DATETIME NOT NULL, -- 생성 일시
    MODIFY_USER_ID VARCHAR(50) NOT NULL, -- 수정 사용자 ID
    MODIFY_DATE DATETIME NOT NULL -- 수정 일시

);


CREATE TABLE USER_ROLE_MAPPING (
    ROLE_SEQ BIGINT PRIMARY KEY NOT NULL-- 롤 시퀀스
    USER_NAME VARCHAR(50), -- 사용자 이름
    PASSWORD VARCHAR(100), -- 비밀번호
    USER_TYPE VARCHAR(3), -- 사용자 유형 (001: 일반, 002: 상담사, 003: 관리자)
    CREATE_USER_ID VARCHAR(50) NOT NULL, -- 생성 사용자 ID
    CREATE_DATE DATETIME NOT NULL, -- 생성 일시
    MODIFY_USER_ID VARCHAR(50) NOT NULL, -- 수정 사용자 ID
    MODIFY_DATE DATETIME NOT NULL -- 수정 일시

);

CREATE TABLE COMMON_CODE (
    COMMON_CODE_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    GROUP_CODE VARCHAR(50),
    CODE VARCHAR(100),
    CREATE_USER_ID VARCHAR(50) NOT NULL,
    CREATE_DATE DATETIME NOT NULL,
    MODIFY_USER_ID VARCHAR(50) NOT NULL,
    MODIFY_DATE DATETIME NOT NULL
);
*/


CREATE TABLE USER (
    USER_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    USER_ID VARCHAR(50) NOT NULL, -- 사용자 ID
    USER_NAME VARCHAR(50) NOT NULL, -- 사용자 이름
    PASSWORD CHAR(60) NOT NULL, -- 비밀번호
    USER_TYPE VARCHAR(3) NOT NULL, -- 사용자 유형 (001: 일반, 002: 상담사, 003: 관리자)
    CREATE_USER_ID VARCHAR(50) NOT NULL, -- 생성 사용자 ID
    CREATE_DATE DATETIME NOT NULL, -- 생성 일시
    MODIFY_USER_ID VARCHAR(50) NOT NULL, -- 수정 사용자 ID
    MODIFY_DATE DATETIME NOT NULL -- 수정 일시

);

CREATE TABLE INQUIRY (
    INQUIRY_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL, -- 문의 순번
    INQUIRY_USER_ID VARCHAR(50) NOT NULL, -- 문의 사용자 ID
    TITLE VARCHAR(100) NOT NULL, -- 제목
    STATUS VARCHAR(3) NOT NULL, -- 상태 (001: 등록, 002: 담당자지정, 003: 답변완료)
    INQUIRY_CONTENT VARCHAR(500) NOT NULL, -- 문의 내용
    INQUIRY_DATE DATETIME NOT NULL, -- 문의 일시
    ANSWER_USER_ID VARCHAR(50) DEFAULT NULL, -- 답변 사용자 ID 
    ANSWER_CONTENT VARCHAR(500) DEFAULT NULL, -- 답변 내용
    ANSWER_DATE DATETIME DEFAULT NULL, -- 답변 일시
    CREATE_USER_ID VARCHAR(50) NOT NULL, -- 생성 사용자 ID
    CREATE_DATE DATETIME NOT NULL, -- 생성 일시
    MODIFY_USER_ID VARCHAR(50) NOT NULL, -- 수정 사용자 ID
    MODIFY_DATE DATETIME NOT NULL -- 수정 일시
);



INSERT INTO USER (USER_SEQ, USER_ID, USER_NAME, PASSWORD, USER_TYPE, CREATE_USER_ID, CREATE_DATE, MODIFY_USER_ID, MODIFY_DATE) VALUES
(1, 'muzi', '무지', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),
(2, 'con', '콘', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),
(3, 'apeach', '어피치', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),
(4, 'jayg', '제이지', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),
(5, 'frodo', '프로도', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),
(6, 'neo', '네오', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '001', 'admin', NOW(), 'admin', NOW()),

(7, 'tube', '튜브', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '002', 'admin', NOW(), 'admin', NOW()),
(8, 'ryan', '라이언', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '002', 'admin', NOW(), 'admin', NOW()),
(9, 'chunsik', '춘식', '$2a$10$vbS2sj9mvPOvJ4bbsfavbO2dbBSgqJ0JPFg.veLumRpGej4AiZKhS', '002', 'admin', NOW(), 'admin', NOW())
;

-- 001 : 13개
-- 002 : 5개
-- 003 : 2개
INSERT INTO INQUIRY (INQUIRY_SEQ, INQUIRY_USER_ID, TITLE, STATUS, INQUIRY_CONTENT, INQUIRY_DATE, ANSWER_USER_ID, ANSWER_CONTENT, ANSWER_DATE, CREATE_USER_ID, CREATE_DATE, MODIFY_USER_ID, MODIFY_DATE) VALUES
(1, 'frodo', '서류전형 문의', '003', '카카오페이 서류전형 문의합니다.', '2022-09-23 01:20:35', 'tube', '서류전형에 대한 문의 답변입니다.', '2022-09-23 01:30:35', 'admin', NOW(), 'admin', NOW()),
(2, 'muzi', '과제전형 문의', '002', '카카오페이 과제전형 문의합니다.', '2022-09-23 02:21:35', 'ryan', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(3, 'neo', '코딩테스트 문의', '002', '카카오페이 코딩테스트 문의합니다.', '2022-09-23 03:21:35', 'chunsik', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(4, 'con', '임원면접 문의', '001', '카카오페이 임원면접 문의합니다.', '2022-09-23 04:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(5, 'apeach', '비밀번호 찾기 문의', '001', '채용사이트 비밀번호 찾기 문의합니다.', '2022-09-23 05:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(6, 'jayg', '채용절차 문의', '001', '카카오페이 채용절차 문의합니다.', '2022-09-23 06:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(7, 'neo', '워크플랫폼팀 문의', '001', '팀 구성에 대한 문의합니다.', '2022-09-23 07:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(8, 'jayg', '레퍼런스 문의', '001', '채용 과정 중 레퍼런스 문의합니다.', '2022-09-23 08:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(9, 'frodo', '기업문화 문의', '001', '카카오페이 기업문화 문의합니다.', '2022-09-23 09:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(10, 'con', '서류탈락 문의', '002', '카카오페이 서류탈락 문의합니다.', '2022-09-23 10:21:35', 'tube', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(11, 'neo', '서류합격 문의', '001', '카카오페이 서류합격 문의합니다.', '2022-09-23 11:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(12, 'frodo', '면접합격 문의', '001', '카카오페이 면접합격 문의합니다.', '2022-09-23 12:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(13, 'apeach', '면접탈락 문의', '003', '카카오페이 면접탈락 문의합니다.', '2022-09-23 13:21:35', 'ryan', '카카오페이 면접 탈락에 대한 답변입니다.', '2022-09-23 13:50:35', 'admin', NOW(), 'admin', NOW()),
(14, 'muzi', '온라인 면접 응시방법 문의', '001', '카카오페이 온라인 면접 응시방법 문의합니다.', '2022-09-23 14:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(15, 'con', '면접시간 문의', '001', '카카오페이 면접시간 문의합니다.', '2022-09-23 15:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(16, 'muzi', '처우협의 문의', '001', '카카오페이 처우협의 문의합니다.', '2022-09-23 16:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(17, 'apeach', '오프라인 면접 응시방법 문의', '001', '오프라인 면접 응시방법 문의합니다.', '2022-09-23 17:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(18, 'con', '건강검진 문의', '002', '카카오페이 건강검진 문의합니다.', '2022-09-23 18:21:35', 'ryan', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(19, 'muzi', '최종합격 문의', '002', '카카오페이 최종합격 문의합니다.', '2022-09-23 19:21:35', 'chunsik', NULL, NULL, 'admin', NOW(), 'admin', NOW()),
(20, 'jayg', '재응시 가능여부 문의', '001', '카카오페이 재응시 가능여부 문의합니다.', '2022-09-23 20:21:35', NULL, NULL, NULL, 'admin', NOW(), 'admin', NOW())
;