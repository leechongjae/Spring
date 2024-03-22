package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.Account;
import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.PersonalAccount;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

	@Bean
	public Account accountGenerator() {

		return new PersonalAccount(20, "110-234-567890");
	}

	@Bean
	public MemberDTO memberGenerator() {

		/* 설명. accountGenerator 메소드를 호출해 반환받은 Account 빈을 MemberDTO의 생성자를 통해 주입 */
		/* 설명. 우리는 생성자 주입 방식을 사용할 것이다. */
//		 return new MemberDTO(1, "홍길동", "010-1234-5678", "hong123@gmail.com", accountGenerator());

		/* 설명. accountGenerator 메소드를 호출해 반환받은 Account 빈을 setter를 통해 주입. */
		MemberDTO member = new MemberDTO();
		member.setSequence(1);
		member.setName("홍길동");
		member.setPhone("010-1234-5678");
		member.setEmail("hong123@gmail.com");
		member.setPersonalAccount(accountGenerator());

		return member;
	}

}
