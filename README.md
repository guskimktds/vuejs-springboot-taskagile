# TaskAgile

Open source task management tool built with Vue.js 2, Spring Boot 2, and MySQL 5.7+

## Local development

Create `src/main/resources/application-dev.properties` with the following settings to override the settings in `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_agile?useSSL=false
spring.datasource.username=<your username>
spring.datasource.password=<your password>
```

## Commands

- Use `mvn install` to build both the front-end and the back-end
- Use `mvn test` to run the tests of the back-end and the front-end
- Use `mvn spring-boot:run` to start the back-end
- Use `npm run serve` inside the `front-end` directory to start the front-end
- Use `java -jar target/app-0.0.1-SNAPSHOT.jar` to start the bundled application


## Frontend Vue.js 와 Backend Springboot 를 tdd base 로 통합

- front-end TDD 작성
   경로 : front-end > tests > unit 

- 회원가입 폼을 위한 테스트 코드 작성하기 : RegisterPage.spec.js
-- 데이터 모델의 초기값 테스트
-- 데이터 모델과 폼 입력 필드 간의 바인딩 테스트
-- 폼 이벤트 핸들러의 존재 여부 테스트

vue-test-utils 라이브러리에 mount 함수를 사용해서 마운트와 렌더링이 완료된 RegisterPage.vue 컴포넌트를 포함하는 Wrapper 객체를 생성한다.

## 백엔드 검증
- 헥사고날아키텍처 사용
-> HTTP 요청이 서버에 도착하면, 어댑터가 요청을 처리한다. (어댑터 = 컨트롤러에 존재하는 핸들러 : 핸들러가 데이터 검증을 수행)
-> 비지니스로직을 폼데이터의 검증로직과 분리 : 특정규칙을 기반으로 데이터의 유효성만 검사
-> 비지니스 룰과 연관되는 검증은 애플리케이션 코어에 존재하는 서비스의 책임
-> RegistrationPayload 클래스를 생성해서 요청한 바디를 통해 전달된 필드를 매핑한다.
-> 스프링MVC 가 RegistrationPayload 클래스를 인스턴스화 하고 요청바디에 있는 데이터로 채워준다.

- RegistrationPayloadTests 단위테스트 클래스를 작성
-> javax.validation.Validator 인스턴스를 생성해서 데이터 검증을 수행
-> TEST 메소드 명명규칙 : [작업단위_테스트 중인 상태_예상되는 행동]

- RegistrationApiController 생성하기
-> UserService 타입의 service 필드를 가진다.
-> register(RegistrationCommand) API 를 제공한다. 성공하면 아무것도 반환하지 않고, 실패하면 RegistrationException 을 던진다.
-> 스프링MVC는 @Valid 어노테이션이 존재하면 register() 메소드에 데이터를 전달하기 전에 RegistrationPayload의 데이터 검증을 우선 수행하여 데이터가 유효한지 확인한다.
-> 핸들러에서는 RegistrationPayload 인스턴스를 RegistrationCommand 클래스로 변환, 서비스 API 를 호출한다.

- RegistrationCommand 읽기전용 클래스
-> 세터는 제공하지 않고 게터만 제공하기 때문에 생성 후 상태를 변경할 수 없다.
-> equals(), hashCode() 로 단위테스트 시 인스턴스를 비교한다.

<tip> Assert </tip>
Assert는 단순히 if문을 줄이는 역할만 하는 것은 아닙니다. 프로젝트 규칙을 적용하고 공통을 재사용한다는 것에 큰 의미가 있습니다.
Spring Assert는 인수를 검증하고 조건에 맞지 않는 경우 IllegalArgumentException 또는 IllegalStateException를 발생시킵니다. 
Assert.hasText(value,"null 이면 해당문구로 exception 을 처리한다.")
출처 url : https://eblo.tistory.com/63

- RegistrationApiControllerTests 단위테스트 작성
-> register(RegistraitonPayload) 메소드를 테스트하기위해 스프링의 MockMvc를 통해서 입력을 제공

- UserService 작성


