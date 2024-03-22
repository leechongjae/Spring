package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

	public static void main(String[] args) {

		/* 설명. XML 설정 파일을 기반으로 ApplicationContext 객체 생성 */
		ApplicationContext context = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

		/* 설명. 컨테이너가 관리중인 MemberDTO 타입의 빈 가져오기 */
		MemberDTO member = context.getBean(MemberDTO.class);

		/* 설명. 위에서 가져온 빈에 은행 코드가 20인 PersonalAccount 객체가 주입되어 있음을 확인. */
		System.out.println(member.getPersonalAccount());

		System.out.println(member.getPersonalAccount().deposit(10000));     // 10000원 입금
		System.out.println(member.getPersonalAccount().getBalance());               // 잔액 출력
		System.out.println(member.getPersonalAccount().withDraw(5000));     // 5000원 출금
		System.out.println(member.getPersonalAccount().getBalance());               // 잔액 조회

		/* Note. 해당 DI 챕터는 개념이 중요한 챕터이지 문법이 중요한 챕터가 아님.
		 *  즉, 개념을 잘 이해해둬야 나중에 문법이나 표현법이라도 찾아볼 수 있음.
		* */
	}

}
