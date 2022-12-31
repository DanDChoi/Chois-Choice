package choi.choice.controller;


import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
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
    @Test
    public void 가입() {
        mbr m = mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo(1L)
                .build();

        mbr savedMbr = mbrRepository.save(m);

        Assertions.assertEquals(m.getMbrNm(), savedMbr.getMbrNm());

    }

    @Test
    public void ID로검색테스트(){
        mbr m = mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo(1L)
                .mbrId("testId")
                .build();
        mbr savedMbr = mbrRepository.save(m);
//        mbr findMbr = mbrRepository.findById(savedMbr.getMbrId());

//        Assertions.assertEquals(findMbr.getMbrId(), savedMbr.getMbrId());
    }


}


