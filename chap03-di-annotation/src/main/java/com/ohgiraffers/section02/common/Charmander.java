package com.ohgiraffers.section02.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
/* 설명. @Primary:
 *  @Autowired로 동일한 타입의 여러 빈(후보)을 찾게 되는 경우가 발생했을 때,
 *  @Primary 어노테이션이 설정된 빈이 선택되어 해당 빈 하나만 주입된다.
 *  단, 동일한 타입의 클래스 중 한 개만 @Primary 어노테이션을 사용할 수 있다.(두 개 이상 정의하면 에러 발생)
 * */
@Primary
public class Charmander implements Pokemon {

	@Override
	public void attack() {
		System.out.println("파이리 불꽃 공격");
	}

}
