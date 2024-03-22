package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Owner {
	
	/* 설명. init-method와 같은 설정 어노테이션이다. */
	@PostConstruct
	public void openShop() {
		System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.");
	}
	
	/* 설명. destroy-method와 같은 설정 어노테이션이다. */
	@PreDestroy
	public void closeShop() {
		System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.");
	}
	
}
