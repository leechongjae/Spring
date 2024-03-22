package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.MemberDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/* 설명.
 *  section03에서는 ComponentScan과 관련하여 'Java 클래스 방식'과 'xml 방식'으로 사용하는 방법을 확인한다.
 *  이 중에서도 'Java 클래스 방식'과 '@ComponentScan 방식'을 자주 사용하는 추세다.
* */

/* 2-4. Java Annotation 예제 */
public class Application {
    public static void main(String[] args) {

        /* 설명. ComponentScan
         *  base package로 설정 된 하위 경로에 특정 어노테이션을 가지고 있는 클래스를 bean으로 등록하는 기능이다.
         *  @Component 어노테이션이 작성 된 클래스를 인식하여 bean으로 등록한다.
         *  특수 목적에 따라 세부 기능을 제공하는 @Controller, @Service, @Repository, @Configuration 등을 사용한다.
         * */

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* 설명. getBeanDefinitionNames : 스프링 컨테이너에서 생성 된 bean들의 이름을 배열로 반환한다. */
        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            System.out.println("beanName : " + beanName);
        }

        MemberDAO memberDAO = context.getBean("memberDAO", MemberDAO.class);

        System.out.println(memberDAO.selectMember(1));
        System.out.println(memberDAO.insertMember(new MemberDTO(3, "user03", "pass03", "신사임당")));
        System.out.println(memberDAO.selectMember(3));

    }
}
