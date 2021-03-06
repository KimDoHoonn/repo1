# 웹 애플리케이션 만들기
## 개요
### 웹 애플리케이션이란?
- 웹 서버가 실행시키는 애플리케이션.
- 작은 크기의 애플리케이션들로 구성된다.
- 작은 크기의 애플리케이션을 "서블릿(Servlet)" 이라 부른다.
    Servlet = Server + Application + let(작은 것을 의미하는 접미사)

### 웹 애플리케이션의 구성 요소
- 서블릿(Servlet) : 업무를 처리하는 객체.
- 필터(Filter) : 요청 처리 전/후에 작업을 수행하는 객체. 
- 리스너(Listner) : 웹 서버나 웹 애플리케이션의 특정 상태를 모니터링하다가
                      특정 사건에 대해 작업을 수행하는 객체.

### 웹 애플리케이션 서버(Web Application Server)
- WAS = Web Server + 웹 애플리케이션을 관리하는 S/W
- 웹 애플리케이션을 관리하는 S/W를 "서블릿 컨테이너(Servlet Container)"라고 부른다.

### CGI(Common Gateway Interface) 프로그램?
- 예전에는 웹 애플리케이션을 CGI 프로그램이라 불렀다.
- CGI는 웹 서버와 프로그램 사이의 데이터 전송 규칙이다.
- 웹 서버가 실행시킬 수 있는 프로그램을 만드려면, 
  반드시 CGI 규칙에 따라야 했다.
    
## 서블릿 만들기
### javax.servlet.Servlet 구현체를 만든다.
- servlet/HelloServlet.java
- javax.servlet.Servlet 인터페이스는 servlet-api 라이브러리에 있다.
  따라서 servlet-api 라이브러리를 추가하라!

### 서블릿을 WAS(톰캣)에 배치한다(deploy).
- PC에 프로그램을 배치하는 것을 보통 "install" 또는 "setup"이라고 표현한다.
  배치 장소는 "c:\Program files" 폴더이다.
- WAS에 웹 애플리케이션을 배치하는 것을 "deploy"라고 표현한다.
  배치 장소는 WAS에 따라 다르다. 문서를 확인하라!
  톰캣의 경우, "톰캣설치폴더/webapps" 이다.
- 배치 작업
  1) webapps 폴더 밑에 웹 애플리케이션 폴더(web01)를 생성한다.
  2) 웹 애플리케이션 폴더(web01) 밑에 "WEB-INF/classes" 폴더를 생성한다.
  3) 컴파일된 클래스 파일을 classes 폴더에 둔다.
     단 패키지에 소속된 클래스라면 그 패키지 폴더를 생성한 후 두어야 한다.
  4) 배치하는 서블릿 클래스에 대한 정보를 "배치 기술서(Deploy Descriptor)" 파일에 기록한다.
     => WEB-INF/web.xml 
     => web.xml 파일은 기존에 있는 예제 웹 애플리케이션 폴더에서 복사해 사용하라! 
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

  <!-- 서블릿 클래스 정보 등록 -->
  <servlet>
    <servlet-name>hello servlet</servlet-name>
    <servlet-class>servlet.HelloServlet</servlet-class>
  </servlet>
  
  <!-- 서블릿에 URL을 부여 -->
  <servlet-mapping>
    <servlet-name>hello servlet</servlet-name>
    <url-pattern>/test/hi</url-pattern>
  </servlet-mapping>
</web-app>

### WAS에 배치된 서블릿 실행하기
- WAS 재시작!
  [Windows OS]
    > shutdown.bat 또는 그냥 콘솔창을 닫는다.
    > startup.bat 또는 파일 탐색기에서 startup.bat 파일을 더블 클릭한다.
  [Mac OS]
    > ./shutdown.sh
    > ./startup.sh
  
- 웹 브라우저 주소창에 다음과 같이 URL을 지정하여 WAS에 서블릿 실행을 요청하라!
      http://서버주소:포트번호/웹애플리케이션이름/서블릿URL
  예) http://localhost:8080/web01/test/hi
      서버주소 => localhost
      포트번호 => 8080
      웹애플리케이션이름 => /web01
      서블릿 URL => /test/hi

## 이클립스를 사용하여 테스트용 톰캣 배치 폴더 만들기
- 한 웹 애플리케이션을 여러 고객사에 배치해야 할 경우,
  각 고객사 별로 배치 폴더를 만들어 테스트 하는 것이 편리하다.
- 고객사 환경에 맞추어 배치 폴더를 만들자!

### 1. 이클립스에 톰캣 서버가 설치된 폴더를 등록한다.
- 이클립스 환경 설정에서 WAS 서버를 등록한다 
  server > Runtime Environments > Add 

### 2. 테스트용 배치 폴더 생성한다.
  Servers 뷰 > 컨텍스트 메뉴 > New > Server

### 3. 테스트용 배치 폴더의 위치를 확인한다.
  워크스페이스폴더/.metadata/.plugins/org.eclipse.wst.server.core/tmp번호


## 이클립스용 웹 애플리케이션 프로젝트 만들기
- 테스트용 배치 폴더에 자동으로 웹 애플리케이션을 배치하려면,
  이클립스에 규칙에 따라 "웹 애플리케이션 프로젝트"를 만들어야 한다.
### 1. Gradle 설정 파일에 'eclipse-wtp', 'war' 플러그인 추가한다.
  => build.gradle 파일 편집
// 이클립스 WTP의 설정 파일을 만들 때 제공할 정보.
eclipse {
  wtp {
    facet {
      facet name: 'jst.web', version: '3.1'    // 사용할 서블릿 기술 버전
      facet name: 'jst.java', version: '1.8'   // 웹 앱을 컴파일할 때 사용할 자바 버전
    }
  }
}   
### 2. Gradle을 사용하여 이클립스 설정 파일을 새로 만든다.
  => gradle cleanEclipse  : 기존 설정 파일을 완전히 지운다.
  => gradle Eclipse       : 새로 설정 파일을 만든다.

### 3. 웹 애플리케이션 자원 및 설정 파일을 위한 폴더를 만든다.
  => HTML, CSS, JavaScript, GIF, JPEG 등 웹 자원의 소스 폴더를 만든다.
  => WEB-INF/ 폴더 아래에 넣을 설정 파일(예: web.xml)을 만든다.
  => 프로젝트폴더/src/main/webapp 폴더에 web.xml 파일을 두어라!
  => HelloServlet 클래스에 대해 서블릿 설정 정보를 추가한다.
  
### 4. 테스트용 톰캣 배치 폴더에 웹 애플리케이션을 배치한다.
  => Servers 뷰 > study 컨텍스트 메뉴 > Add and remove... > web01 추가

### 5. 서버 실행 후 테스트 


   
   
   
   
   
