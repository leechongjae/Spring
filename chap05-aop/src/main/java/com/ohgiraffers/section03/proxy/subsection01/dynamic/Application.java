package com.ohgiraffers.section03.proxy.subsection01.dynamic;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import com.ohgiraffers.section03.proxy.common.Student;

import java.lang.reflect.Proxy;

public class Application {
	
	public static void main(String[] args) {
		
		/* 프록시란?
		 * 실제 Target의 기능을 수행하면서 기능을 확장하거나 추가하는 실제 객체를 의미한다.
		 * 디자인패턴의 프록시 패턴과는 조금 다른 개념이긴 하다.
		 * 
		 * 실제 응용 프로그램을 만들 시 프록시는 직접 기능을 구현할 일은 없다.
		 * 프록시는 대상의 요청을 가로채 기능을 확장시킬 목적으로 사용하며 reflection 기능을 이용해서 간단히 구현해보자
		 *  */
		
		Student student = new OhgiraffersStudent();
		Handler handler = new Handler(student);
		
		/* 클래스로더, 프록시를 만들 클래스 메타 정보(인터페이스만 가능), 프록시 동작할 때 적용될 핸들러 */
		Student proxy = (Student) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[] {Student.class}, handler);
	    
		/* 프록시로 감싸진 인스턴스의 메소드를 호출하게 되면  핸들러에 정의한 메소드가 호출된다. */
		proxy.study(16);
		
		/* 프록시 생성은 크게 두 가지 방식이 제공된다.
		 * 1. JDK Dynamic Proxy 방식
		 * 2. CGLib 방식
		 * 
		 * Aspectj에서 사용하는 프록시 생성은 1번 방식이다.
		 * 이 방식은 리플렉션을 이용해서 proxy클래스를 동적으로 생성해주는 방식인데, 타겟의 인터페이스를 기준으로 proxy를 생성해준다.
		 * 사용자의 요청이 타겟을 바라보고 실행될 수 있도록 타겟 자체에 대한 코드 수정이 아닌 리플렉션을 이용한 방식으로,
		 * 타겟의 위임 코드를 InvocationHandler를 이용하여 작성하게 된다.
		 * 하지만 사용자가 타겟에 대한 정보를 잘못 주입하는 경우가 발생할 수 있기 때문에 내부적으로 주입된 타겟에 대한 검증 코드를 거친 후 invoke가 동작하게 된다.
		 * 
		 * 2번 방식은 동적으로 Proxy를 생성하지만 바이트코드를 조작하여 프록시를 생성해주는 방식이다.
		 * 인터페이스 뿐 아니라 타겟의 클래스가 인터페이스를 구현하지 않아도 프록시를 생성해준다.
		 * 두 방식의 차이점은 성능 차이인데, invoke시 성능이 차이가 나게 된다.
		 * CGLib(Code Generator Library)의 경우에는 처음 메소드가 호출된 당시 동적으로 타켓 클래스의 바이트코드를 조작하게 되고, 
		 * 그 이후 호출시부터는 변경된 코드를 재사용한다.
		 * 따라서 매번 검증 코드를 거치는 1번 방식보다는 성능면에서는 더 빠르게 된다.
		 * 또한 리플렉션에 의한 것이 아닌 바이트코드를 조작하는 방식이기 때문에 성능면에서는 더 우수한 방식이었다.
		 * 
		 * 하지만 CGLib 방식은 단점이 있었고, 스프링에서 기본적으로 제공되는 방식은 아니었기에 별도로 의존성을 추가하여 개발해야 했고,
		 * 파라미터가 없는 default 생성자가 반드시 필요했으며, 생성된 프록시의 메소드를 호출하면 타겟의 생성자가 2번 호출되는 문제점들이 있었다.
		 * 
		 * 스프링 4.3, 스프링부트 1.3 이후부터 CGLib의 문제가 된 부분이 개선되어 기본 core 패키지에 포함되게 되었고,
		 * 스프링에서 기본적으로 사용하는 프록시 방식이 CGLib 방식이 되었다.
		 * */
	    
	}
	
}
