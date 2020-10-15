# 활동내역 정리(2020.10.15)
개발환경구성
Node.js 8.11.0+
JDK 1.8.0_152+
MySQL 5.7+
Git 2.15.1+
코드편집기(IDE) : VSCode
프런트엔드 런타임 : 크롬브라우저
백엔드 런타임 : JRE(JDK 8 포함)
의존관계 및 패키지빌드 : 프런트엔드(Node.js 8.11.0+), 백엔드(Maven 3.2  

#1. 백엔드 뼈대 만들기
-> 스프링 이니셜라이저로 백엔드 뼈대 생성하기
-> https://start.spring.io  
-> Project 명세서작성, Dependencies 추가 : Web, Thymeleaf, JPA, DevTools
-> Generate Project

#2. 소스 형상관리
-> git init -> git add -> git commit 후 git remote 로 원격저장소 생성 후 add
-> git push 로 GitHub 에 저장

#3. 애플리케이션 실행하기
-> 에러확인 후 의존성 추가하기
-> 데이터 소스 설정 추가하기

#4. 프런트엔드 뼈대 생성하기
-> vue-cli (vue.js command line)으로 뼈대 생성
-> npm install -g @vue/cli
-> vue create front-end
-> Babel, Router : 히스토리 모드 활성화
-> Vuex, CSS Pre-processors : SCSS/SASS, Linter : ESLint + Standard, Unit : Jest, E2E : 나이트와치(Nightwatch), NPM : 패키지관리
-> HMR(Hot-Module-Replacement)

#5. TDD
-> Frontend TDD 만들고 테스트 하기
-> 단위테스트 만들기 : test> unit> LoginPage.spec.js 로 단위테스트 하기
-> e2e 테스트 만들기 : test> e2e> specs> login.e2e.js 로 e2e 테스트 하기

#6. 프런트엔드와 백엔드 연결하기
-> mvn, npm 을 활용하여 전체 애플리케이션 빌드하기
-> 프런트엔드 애플리케이션 http://localhost:3000/ -> 백엔드 애플리케이션 http://localhost:8080/ 요청 시 cross-origin 발생(브라우저에 의해 막힘)
-> 백엔드 HTTP 응답헤더에 Acess-Control-Allow-Origin 추가로 처리 가능(프런트엔드와 백엔드서버가 분리된 경우)
-> 프런트엔드에 HTTP 프락시를 추가 -> 프락시가 백엔드 요청을 전달하는 방식(프런트엔드와 백엔드가 같은 패키지로 놓고 하나의 서버에 배포된 경우)

#6-1 하나의 명령어로 빌드하기
-> 빌드 라이프 사이클 만들기 :
backend initialize -> backend compile -> backend test
-> prepare-package -> front-end npm install -> front-end npm run unit
-> front-end npm run build -> copy resource
-> package -> pre-integration-test
-> spring-boot:start -> integration test -> front-end npm run e2e
-> post-integration-test -> spring-boot:stop
-> install

#6-2 통합 빌드 프로세스 구현하기
-> Exec Maven Plugin : npm 실행
-> Maven Resource Plugin : 프런트엔드 리소스 복사
-> SpringBoot Maven Plugin : 애플리케이션 시작, 종료
-> Maven Clean Plugin : 빌드에 영향을 줄 리소스를 제거

# front-end

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your unit tests
```
npm run test:unit
```

### Run your end-to-end tests
```
npm run test:e2e
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
