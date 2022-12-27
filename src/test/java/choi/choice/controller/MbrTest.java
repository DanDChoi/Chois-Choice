package choi.choice.controller;


import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class MbrTest {

    @Autowired
    private MbrRepository mbrRepository;
    @Autowired
    private MemberService memberService;

    public MbrTest(MbrRepository mbrRepository) {
        this.mbrRepository = mbrRepository;
    }

    @ParameterizedTest
    @Transactional
    public void 가입() {
        mbr mbr = mbrRepository.save(new mbr("홍길동"));
        String email = "test@naver.com";

    }

}


