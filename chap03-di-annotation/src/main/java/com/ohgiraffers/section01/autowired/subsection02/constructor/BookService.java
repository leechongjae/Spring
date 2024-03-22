package com.ohgiraffers.section01.autowired.subsection02.constructor;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. @Service:
 *  @Component의 세분화 어노테이션의 한 종류로 Service 계층에서 사용한다.
 * */
@Service("bookServiceConstructor")
public class BookService {

    /* Note. subsection01의 BookService과 메서드 선언부 동일함.*/

    private final BookDAO bookDAO;

    /* 설명. 생성자와 @Autowired를 이용해 의존성 주입:
     *  BookService의 기본 생성자가 아닌, BookDAO 타입을 매개변수로 하는 매개변수가 있는 생성자를 작성한다.
     *  그리고 나서 이 매개변수 생성자에 @Autowired 어노테이션을 작성하면
     *  BookService 객체를 생성하고자 생성자가 호출될 때 Spring이 자동으로 BookDAO 타입의 빈 객체를 생성해 주입해준다.
     * */
    /* BookDAO 타입의 빈 객체를 생성자에 자동으로 주입해준다. */
    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    /* 필기. 생성자 주입 방식의 장점
     *  1. 객체 간 의존성 보장
     *   - 순환 참조(circular reference)를 Spring 실행 시점에 에러를 발생시키기 때문에 런타임 에러를 방지할 수 있다.
     *   - 필드 주입 방식 및 setter 주입 방식은 메소드 실행 시점, 즉 런타임에 에러를 발생시킨다.
     *  2. 객체의 불변성 보장
     *   - 필드부에 final 키워드를 사용해 객체가 한 번 생성된 이후 이를 건들 수 없다.
     *   - 즉, 원본 인스턴스의 값이 중간에 오염될 수 없기 때문에 객체의 안정성 및 불변성을 보장한다.
     *  3. 코드 가독성
     *   - 해당 객체가 어떤 의존성을 가지고 있는지 한 눈에 명확히 알 수 있다.
     *   - 필드 주입 방식 및 setter 주입 방식은 이 의존성들을 일일이 찾아 봐야 하기 때문에 한 눈에 파악하기 힘들다.
     *  4. 테스트 용이성
     *   - 스프링 컨테이너 없이 순수 Java 코드로 테스트 가능하다.
     *   - 특히 단위 테스트(unit test) 시 편리하고 속도도 빠르다.
     * */

    /* 필기
     *  Spring 4.3 버전 이후, 생성자가 한 개만 존재할 때 @Autowired 어노테이션을 생략해도 자동으로 해당 생성자를 통해 의존성 주입이 동작한다.
     *  단, 생성자가 1개 이상 존재한다면 @Autowired를 명시적으로 작성해줘야 한다.
     *  즉, 위의 코드에서 @Autowired 어노테이션을 삭제하고 기본 생성자를 추가해 어노테이션 없이 생성자가 2개 존재하는 상황이 된다면
     *  Spring은 자동으로 @Autowired 어노테이션을 작성해주지도 않고 생성자 주입도 동작하지 않게 된다.
     * */

    public List<BookDTO> selectAllBooks(){

        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence) {

        return bookDAO.selectOneBook(sequence);
    }

}