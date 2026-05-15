# KUIT 7주차 미션 — JPA로 배달앱 REST API 구현하기

## 시작 전 설정

1. 환경변수 설정 — `application.yml`에 아래 환경변수를 본인 로컬 환경에 맞게 주입

```yaml
spring:
  datasource:
    url: ${DATASOURCE_URL_LOCAL}      # jdbc:mysql://localhost:3306/{생성한 DB 이름}
    username: ${DATASOURCE_USERNAME}  # 본인 DB username
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

## 구현한 API 목록 (10개)

| Method | URI                                       | 설명                        | 페이징 |
|--------|-------------------------------------------|-----------------------------|--------|
| POST   | /categories                               | 카테고리 등록               | -      |
| GET    | /categories                               | 카테고리 목록 조회          | ✓      |
| POST   | /restaurants                              | 가게 등록                   | -      |
| GET    | /restaurants?categoryId=&page=&size=      | 가게 목록 조회 (카테고리 필터) | ✓      |
| GET    | /restaurants/{restaurantId}               | 가게 상세 조회              | -      |
| POST   | /restaurants/{restaurantId}/menus         | 메뉴 등록                   | -      |
| GET    | /restaurants/{restaurantId}/menus         | 가게 메뉴 목록 조회         | ✓      |
| POST   | /orders                                   | 주문 생성                   | -      |
| GET    | /members/{memberId}/orders                | 회원 주문 목록 조회         | ✓      |
| DELETE | /orders/{orderId}?memberId=               | 주문 취소 (soft delete)     | -      |

### 적용한 체크리스트
- 모든 연관관계 `FetchType.LAZY`
- Request/Response DTO 분리 (`dto/request`, `dto/response`)
- `@Valid` 입력값 검증 + `GlobalExceptionHandler`로 400 응답 매핑
- 클래스 단위 `@Transactional(readOnly = true)`, 변경이 있는 메서드만 `@Transactional`
- 페이징은 Spring Data `Pageable` + 응답 래퍼 `PageRes<T>` 사용

### 기타 특이사항
- `Order`의 진행 상태(`orderStatus`)와 soft-delete 플래그(`status`)를 분리. 취소는 `PLACED` 상태에서만 허용.
- 주문 생성 시 메뉴 가격을 `priceAtOrder`에 스냅샷 저장 → 이후 메뉴 가격 변경에 영향 받지 않음.
- 인증/인가는 8주차 범위라 `DELETE /orders/{orderId}`는 `memberId`를 RequestParam으로 받아 소유자 검증.

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