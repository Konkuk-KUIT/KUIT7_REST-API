# KUIT 7주차 미션 — JPA로 배달앱 REST API 구현하기

## 시작 전 설정

1. 환경변수 설정 — `application.yml`에 아래 환경변수를 본인 로컬 환경에 맞게 주입

```yaml
spring:
  datasource:
    url: ${DATASOURCE_URL_LOCAL}      # jdbc:mysql://localhost:3306/{생성한 DB 이름}
    name: ${DATASOURCE_NAME}  # 본인 DB name
    password: ${DATASOURCE_PASSWORD}  # 본인 DB 비밀번호
    driver-class-name: com.mysql.cj.jdbc.Driver
```

2. MySQL에서 DB 생성 (테이블 이름은 자유)

```sql
CREATE DATABASE baemin DEFAULT CHARACTER SET utf8mb4;
```

---

## 완성된 API (참고용)

| Method | URI | 설명 |
|--------|-----|------|
| POST | /members | 회원 가입 |
| POST | /members/login | 로그인 |
| GET | /members/{memberId} | 회원 단건 조회 |

---

## 미션: 직접 구현할 API

6주차에 설계한 본인의 ERD를 기반으로 **10개** API를 자유롭게 설계하고 구현하세요.
API 종류와 URI는 본인 ERD에 맞게 결정하면 됩니다.
단, GET/POST/DELETE 등.. 다양하게 섞어서 구현해주세요

> 조회 API의 경우 페이징 처리를 적용하여 API 성능을 개선해보세요! (선택)

---

## PR 메시지 작성 방법

PR 본문에는 본인이 구현한 **10개 API 목록**을 아래 형식으로 작성해주세요.

### 구현한 API 목록

| Method | URI                 | 설명       |
|--------|---------------------|----------|
| POST   | /members            | 회원 가입    |
| ...    | ...                 | ...      |

#### 기타 특이사항
(페이징 적용 여부, 어려웠던 점, 코드 설명 등 자유롭게 작성)

---

## API 문서화

구현한 API를 Postman 또는 Swagger로 문서화한 뒤 URL을 노션 미션 페이지에 제출하세요.