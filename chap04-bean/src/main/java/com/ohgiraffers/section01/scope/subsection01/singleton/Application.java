package com.ohgiraffers.section01.scope.subsection01.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	/* 필기. Bean Scope:
	 *  스프링 빈이 생성될 때 생성되는 인스턴스의 범위를 의미한다. 스프링에서는 다양한 Bean scope를 제공한다.
	 *  - Singleton: 하나의 인스턴스만을 생성하고, 모든 빈이 해당 인스턴스를 공유하여 사용한다.
	 *  - prototype: 매번 새로운 인스턴스를 생성한다.
	 *  - request: HTTP 요청을 처리할 때마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다.
	 *             웹 애플리케이션 컨텍스트에만 해당된다.
	 *  - session: HTTP 세션 당 하나의 인스턴스를 생성하고, 세션이 종료되면 인스턴스를 폐기한다.
	 *             웹 애플리케이션 컨텍스트에만 해당된다.
	 *  - globalSession: 전역 세션 당 하나의 인스턴스를 생성하고, 전역 세션이 종료되면 인스턴스를 폐기한다.
	 *                   포털 애플리케이션 컨텍스트에만 해당된다.
	 * */
	/* Note. Bean의 Life-Cycle을 정의하는 역할은 중~고급 개발자가 설계 당시에 지정하므로 개념 위주로 알아둔다. */
	public static void main(String[] args) {

		/* 설명. 빈 설정 파일을 기반으로 IoC 컨테이너 생성 */
		ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName : beanNames) {
			System.out.println("beanName : " + beanName);
		}

		/* 설명. 붕어빵, 딸기우유, 지리산 암반수 등의 빈 객체를 반환 받는다. */
		Product carpBread = context.getBean("carpBread", Bread.class);
		Product milk = context.getBean("milk", Beverage.class);
		Product water = context.getBean("water", Beverage.class);

		/* 설명. 첫 번째 손님이 쇼핑 카트를 꺼낸다. */
		ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
		cart1.addItem(carpBread);
		cart1.addItem(milk);

		/* 설명. 붕어빵과 딸기우유가 담겨있다. */
		System.out.println("cart1에 담긴 내용 : " + cart1.getItem());

		/* 설명. 두 번째 손님이 쇼핑 카트를 꺼낸다. */
		ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
		cart2.addItem(water);

		/* 설명. 붕어빵과 딸기우유와 지리산암반수가 담겨있다. */
		System.out.println("cart2에 담긴 내용 : " + cart2.getItem());

		/* 설명. 두 카트의 hashcode를 출력해보면 동일한 것을 볼 수 있다. */
		System.out.println("cart1의 hashcode : " + cart1.hashCode());
		System.out.println("cart2의 hashcode : " + cart2.hashCode());

		/* 설명.
		 *  스프링 컨테이너(IoC Container)는 Bean의 기본 스코프를 singleton으로 관리한다..
		 *  singleton 스코프를 갖는 Bean은 애플리케이션 내에서 유일한 인스턴스를 갖는다.
		 *  이 예제에서 손님 두 명이 각각 쇼핑 카트를 이용해 상품을 담았을 때
		 *  singleton으로 관리되는 cart는 두 손님이 동일한 카트에 물건을 담게 된다.
		 */
	}

}
