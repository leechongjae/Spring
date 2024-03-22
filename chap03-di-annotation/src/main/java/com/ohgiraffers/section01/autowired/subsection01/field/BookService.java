package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. @Service:
 *  @Component의 세분화 어노테이션의 한 종류로 Service 계층에서 사용한다.
 * */
@Service("bookServiceField")
public class BookService {

    /* 설명. 아래 3가지는 ComponentScan 범위 안에 어노테이션이 정의된 클래스가 존재할 때 유효하다.
     *  1. @Service에 의해서 BookService 타입의 bookService 빈이 관리된다.
     *  2. BookDAOImpl에 있는 @Respository에 의해 bookDAOImpl 빈이 관리된다(동시에 BookDAO 타입이기도 하다).
     *  3. @autowired에 의해서 BookDAO 타입의 빈이 BookService의 필드인 bookDAO 필드에 주입(=대입)된다.
    * */
    @Autowired
    private BookDAO bookDAO;    // 설명. ComponentScan의 범위 안에 BookDAO 타입의 bean이 필드 주입됨.
    /* Note. 이는 실제로 private BookDAO bookDAO = new BookDAOImpl();과 같음. */

    /* 설명. 도서 목록 전체 조회 */
    public List<BookDTO> selectAllBooks(){

        return bookDAO.selectBookList();
    }

    /* 설명. 도서 번호로 도서 조회 */
    public BookDTO searchBookBySequence(int sequence) {

        return bookDAO.selectOneBook(sequence);
    }
}
