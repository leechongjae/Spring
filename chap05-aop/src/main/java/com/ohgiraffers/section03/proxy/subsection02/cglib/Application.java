package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.Enhancer;

public class Application {
	
	public static void main(String[] args) {
		
		/* 이번에는 인터페이스 없이 GreedyStudent 클래스만 작성하여 CGLib를 이용한 프록시 생성을 확인해볼 것이다. */
		OhgiraffersStudent student = new OhgiraffersStudent();
		Handler handler = new Handler(student);
		
		/* 인터페이스 없이 구현 가능하다 
		 * Enhancer 클래스의 create static 메소드는 타겟 클래스의 메타정보와 핸들러를 전달하면 proxy를 생성해서 반환해준다.
		 * */
		OhgiraffersStudent proxy = (OhgiraffersStudent) Enhancer.create(OhgiraffersStudent.class, new Handler(new OhgiraffersStudent()));

		proxy.study(20);

	}
	
}
