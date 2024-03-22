package com.ohgiraffers.section01.common;

import org.springframework.stereotype.Repository;

import java.util.*;

/* 설명. @Repository:
 *  @Component의 세분화 어노테이션의 한 종류로 DAO 타입의 객체에 사용한다.
*  */
@Repository("bookDAO")
public class BookDAOImpl implements BookDAO{

    private Map<Integer, BookDTO> bookList;

    /* 설명. DB에 가서 조회한 ResultSet이 있다고 가정하기 위한 생성자 */
    public BookDAOImpl() {
        bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1, 123456, "자바의 정석", "남궁성", "도우출판", new Date()));
        bookList.put(2, new BookDTO(2, 654321, "칭찬은 고래도 춤추게 한다", "고래", "고래출판", new Date()));
    }

    @Override
    public List<BookDTO> selectBookList() {
        /* 설명. HashMap의 value만 뽑아 ArrayList 형태로 반환(Map -> List) */
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO selectOneBook(int sequence) {
        return bookList.get(sequence);
    }

}
