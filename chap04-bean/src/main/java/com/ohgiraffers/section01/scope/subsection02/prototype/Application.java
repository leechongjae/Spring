package com.ohgiraffers.section01.scope.subsection02.prototype;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

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

		/* 설명. 지리산암반수가 담겨있다. */
		System.out.println("cart2에 담긴 내용 : " + cart2.getItem());

		/* 설명. 두 카드의 hashcode를 출력해보면 다른 것을 볼 수 있다. */
		System.out.println("cart1의 hashcode : " + cart1.hashCode());
		System.out.println("cart2의 hashcode : " + cart2.hashCode());

		/* 설명.
		 *  ShoppingCart의 bean scope를 prototype으로 설정하자 getBean으로 인스턴스를 꺼내올 때 마다
		 *  새로운 인스턴스를 생성하게 된다.
		 *  따라서 이번 예제에서는 손님 두 명이 각각 쇼핑 카트를 이용해 상품을 담는 상황이 잘 연출되었다.
		 * */
	}

}
