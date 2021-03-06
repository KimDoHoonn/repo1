/* 주제: 리스너 - 개요 및 ServletContextListener
 * => 특정 사건(이벤트)이 발생했을 때 실행되는 객체
 * => 이벤트
 * 1) 웹애플리케이션이 시작되었다.
 * 2) 웹애플리케이션이 종료되었다.
 * 3) 요청이 들어왔다.
 * 4) 요청 전용 창고에 데이터가 입고되었다.
 * 5) 세션이 준비되었다.
 * 6) 세션 전용 창고에 데이터가 입고되었다.
 * ...
 * 
 * => ServletContextListener
 *    - 웹애플리케이션이 시작되거나 종료될 때 호출될 메서드를 정의한 인터페이스
 *    - 이 인터페이스의 구현체는 웹애플리케이션이 시작되거나 종료될 때 마다 실행된다.
 *    - 모든 서블릿을 위한 공용 도구를 준비할 때 주로 사용한다.
 *    
 * => 리스너도 서블릿과 필터처럼 웹 애플리케이션 DD파일에 등록해야 한다.
 *    또는 애노테이션을 붙여야 한다.
 */
package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener01 implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 웹 애플리케이션을 시작한 후 바로 호출한다. by ServletContainer
    System.out.println("Listener01.contextInitialized()");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // 웹 애플리케이션을 종료하기 직전에 호출한다. by ServletContainer
    System.out.println("Listener01.contextDestroyed()");
  }

}











