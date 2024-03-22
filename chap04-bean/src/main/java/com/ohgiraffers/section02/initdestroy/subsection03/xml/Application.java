package com.ohgiraffers.section02.initdestroy.subsection03.xml;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new GenericXmlApplicationContext("section02/initdestroy/subsection03/xml/spring-context.xml");
		
		Product carpBread = context.getBean("carpBread", Bread.class);
		Product milk = context.getBean("milk", Beverage.class);
		Product water = context.getBean("water", Beverage.class);
		
		ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
		cart1.addItem(carpBread);
		cart1.addItem(milk);
		
		System.out.println("cart1에 담긴 내용 : " + cart1.getItem());
		
		ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
		cart2.addItem(water);
		
		System.out.println("cart2에 담긴 내용 : " + cart2.getItem());

		/* 설명.
		 *  init 메소드는 빈 객체 생성 시점(=IoC 컨테이너 동작 시점)에 동작하므로 바로 확인할 수 있지만,
		 *  destroy 메소드는 빈 객체 소멸 시점에 동작하므로 컨테이너가 종료 되지 않을 경우 확인할 수 없다.
		 *  가비지 컬렉터가 해당 빈을 메모리에서 지울 때 destroy 메소드가 동작하게 되는데,
		 *  GC가 메모리에서 빈을 지우기 전에 프로세스(main 메서드)는 종료되기 때문이다.
		 *  따라서 아래와 같이 강제로 컨테이너를 종료시키면 bean들도 소멸되고 destroy 메소드가 동작할 것이다.
		 * */
		((GenericXmlApplicationContext) context).close();
	}

}
