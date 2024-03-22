package com.ohgiraffers.section03.properties.subsection01.properties;


import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
/* 설명. resources 폴더 하위 경로를 기술한다. 폴더의 구분은 슬러쉬(/) 혹은 역슬러쉬(\\)로 한다. */
@PropertySource("section03/properties/subsection01/properties/product-info.properties")
public class ContextConfiguration {

	/* 설명. 치환자(placeholder) 문법을 이용하여 properties에 저장된 key를 입력하면 value에 해당하는 값을 꺼내온다.
	 *  공백을 사용하면 값을 읽어오지 못하니 주의한다.
	 *  : 을 사용하면 값을 읽어오지 못하는 경우 사용할 대체 값을 작성할 수 있다.
	 **/
	@Value("${bread.carpbread.name:팥붕어빵}")
	private String carpBreadName;

	/* 값은 여러 번 불러올 수 있다. */
//	@Value("${bread.carpbread.name:슈크림붕어빵}")
//	private String carpBreadName2;

	@Value("${bread.carpbread.price:0}")
	private int carpBreadPrice;

	@Value("${beverage.milk.name:}")
	private String milkName;

	@Value("${beverage.milk.price:0}")
	private int milkPrice;

	@Value("${beverage.milk.capacity:0}")
	private int milkCapacity;

    /* Note. water 관련은 밑에서 활용함. */

	@Bean
	public Product carpBread() {

		return new Bread(carpBreadName, carpBreadPrice, new java.util.Date());
	}

	@Bean
	public Product milk() {

		return new Beverage(milkName, milkPrice, milkCapacity);
	}

	@Bean
	public Product water(@Value("${beverage.water.name:}") String waterName,
						 @Value("${beverage.water.price:0}") int waterPrice,
						 @Value("${beverage.water.capacity:0}") int waterCapacity) {

		return new Beverage(waterName, waterPrice, waterCapacity);
	}

	@Bean
	@Scope("prototype")
	public ShoppingCart cart() {

		return new ShoppingCart();
	}

}


