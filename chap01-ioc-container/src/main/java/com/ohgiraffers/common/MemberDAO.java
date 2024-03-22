package com.ohgiraffers.common;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/* 설명. DAO
 *  DAO(Data Access Object) 클래스는 Repository 계층과 마찬가지로
 *  java application과 database를 연동시켜주기 위한 계층의 클래스로 사용된다.
 *
 * 설명. @Repository
 *  DB와 연동하기 위해 사용되는 클래스에 추가하는 어노테이션으로,
 *  이 어노테이션이 정의된 클래스 또한 bean으로써 관리된다.
 *  클래스에 계층의 의미(특별한 기능 수행) 없이 단순한 bean으로 관리하고자 하는 객체는 @Component를 사용한다.
 *  따라서 해당 클래스는 @Component가 아닌 @Repository 어노테이션을 정의한다.
* */
//@Component
@Repository
public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();

        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(2, new MemberDTO(2, "user02", "pass02", "유관순"));
    }

    /* 설명. 매개변수로 전달 받은 회원 번호를 map에서 조회 후 회원 정보를 리턴하는 메소드 */
    public MemberDTO selectMember(int sequence) {
        return memberMap.get(sequence);
    }

    /*설명.  매개변수를 전달 받은 회원 정보를 map에 추가하고 성공 실패 여부를 boolean으로 리턴하는 메소드 */
    public boolean insertMember(MemberDTO newMember) {

        int before = memberMap.size();

        memberMap.put(newMember.getSequence(), newMember);

        int after = memberMap.size();

        return after > before ? true : false;
    }
}
