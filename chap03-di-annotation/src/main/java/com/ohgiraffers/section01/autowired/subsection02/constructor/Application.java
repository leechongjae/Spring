package com.ohgiraffers.section01.autowired.subsection02.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        /* Note. subsection01의 Application과 내용 동일함. 복붙을 진행하되,
         *  1. BookService의 빈 이름만 bookServiceConstructor로 수정.
         *  2. 해당 클래스 import 영역에 subsection01과 관련된 import 삭제.
         * */
        /* 필기. AnnotationConfigApplicationContext 생성자에 basePackages 문자열을 전달하여
         *  ComponentScan 개념을 따로 설정 클래스 없이 바로 적용 가능하다.
         * */
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);

        /* 설명. 전체 도서 목록 조회 후 출력 확인 */
        bookService.selectAllBooks().forEach(System.out::println);

        /* 설명. 도서번호로 검색 후 출력 확인*/
        System.out.println(bookService.searchBookBySequence(1));
        System.out.println(bookService.searchBookBySequence(2));
    }

}
