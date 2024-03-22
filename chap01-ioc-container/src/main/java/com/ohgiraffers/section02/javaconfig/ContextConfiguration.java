package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 설명. @Configuration 어노테이션은 해당 클래스가 bean을 생성하는 클래스임을 표기한다.
 *  bean의 이름을 별도로 지정하지 않으면 해당 클래스의 첫 글자를 소문자로 취하여 설정된다.
* */
@Configuration("configurationSection02")
public class ContextConfiguration {

    /* 설명. @Bean 어노테이션은 해당 메소드의 반환 값을 스프링 컨테이너에 bean으로 등록한다는 의미이다.
     *  이름을 별도로 지정하지 않으면 메소드 이름을 bean의 id로 자동 인식한다.
     *  @Bean("myName") 또는 @Bean(name="myName") 의 형식으로 bean의 id를 설정할 수 있다.
    * */
    @Bean(name="member")
    public MemberDTO getMember() {

        return new MemberDTO(1, "user01", "pass01", "홍길동");
    }

}
