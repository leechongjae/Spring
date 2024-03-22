package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        /* 설명. Java 설정 파일을 기반으로 ApplicationContext 객체 생성 */
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* Note. 앞의 xml 방식에서 작성한 코드와 동일하기 때문에 복붙 가능. */
        /* 설명. 컨테이너가 관리중인 MemberDTO 타입의 빈 가져오기 */
        MemberDTO member = context.getBean(MemberDTO.class);

        /* 설명. 위에서 가져온 빈에 은행 코드가 20인 PersonalAccount 객체가 주입되어 있음을 확인. */
        System.out.println(member.getPersonalAccount());

        System.out.println(member.getPersonalAccount().deposit(10000));     // 10000원 입금
        System.out.println(member.getPersonalAccount().getBalance());               // 잔액 출력
        System.out.println(member.getPersonalAccount().withDraw(5000));     // 5000원 출금
        System.out.println(member.getPersonalAccount().getBalance());               // 잔액 조회

    }

}
