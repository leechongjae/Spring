package com.ohgiraffers.section01.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Map<Integer, MemberDTO> selectMembers(){
        System.out.println("target -> selectMembers() 메소드 실행");
        return memberDAO.selectMembers();
    }

    public MemberDTO selectMember(int index) {
        System.out.println("target -> selectMember(Long id) 메소드 실행");
        return memberDAO.selectMember(index);
    }
}
