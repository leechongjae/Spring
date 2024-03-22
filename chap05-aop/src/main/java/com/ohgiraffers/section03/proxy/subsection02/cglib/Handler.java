package com.ohgiraffers.section03.proxy.subsection02.cglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.InvocationHandler;

/* 여기서는 cglib 패키지에 있는 인터페이스를 구현해야 한다. */
public class Handler implements InvocationHandler {
	
	private final OhgiraffersStudent student;
     
    public Handler(OhgiraffersStudent student) {
    	this.student = student;
    }
    
    /* 기존 인터페이스를 구현하는 메소드와 동일하다 */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	
    	System.out.println("============ 공부가 너무 하고 싶습니다. ==============");
    	System.out.println("호출 대상 메소드 : " + method);
    	for(Object arg : args) {
    		System.out.println("전달된 인자 : " + arg);
    	}
    	
    	method.invoke(student, args);
    	 
    	System.out.println("============ 공부를 마치고 수면 학습을 시작합니다. ============");
    	 
    	return proxy;
    	 
    }
}
