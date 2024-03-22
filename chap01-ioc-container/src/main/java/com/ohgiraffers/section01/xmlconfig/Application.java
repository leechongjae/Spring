package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/* 2-2. XML 예제 */
public class Application {
	public static void main(String[] args) {

		/* 설명. GenericXmlApplicationContext 클래스를 사용하여 ApplicationContext를 생성한다.
		 *  생성자에 설정 메타 정보가 기술된 XML 파일의 경로를 인자로 전달해 객체를 생성한다.
		 * */
		ApplicationContext context
				= new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

		/* 설명. 1. bean의 id를 이용해서 bean을 가져오는 방법 */
		MemberDTO member = (MemberDTO) context.getBean("member");

		/* 설명. 2. bean의 클래스 메타 정보를 전달하여 가져오는 방법 */
//		MemberDTO member = context.getBean(MemberDTO.class);

		/* 설명. 3.bean의 id와 클래스 메타 정보를 전달하여 가져오는 방법 */
//		MemberDTO member = context.getBean("member", MemberDTO.class);

		System.out.println(member);

		/* Note. XML 기반 메타 정보 구성 방식 지양
		 *  Spring 공식 문서를 보면 '요즘 많은 개발자들이 Java기반 구성을 선택한다'고 써있다.
		 *  https://docs.spring.io/spring-framework/reference/core/beans/basics.html#beans-factory-metadata
		* */

	}
}