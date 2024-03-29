package choi.choice.controller;


import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MbrTest {

    @Autowired
    private Mbr mbr;
    @Autowired
    private MbrRepository mbrRepository;
    @Autowired
    private MemberService memberService;

    public MbrTest(Mbr mbr, MbrRepository mbrRepository, MemberService memberService) {
        this.mbr = mbr;
        this.mbrRepository = mbrRepository;
        this.memberService = memberService;
    }

    @ParameterizedTest
    public void 가입() {
        Mbr m = mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo(202301151234L)
                .build();

        mbrRepository.save(m);

        Mbr savedMbr = mbrRepository.findByEmail("test@naver.com");

        Assertions.assertEquals(m.getMbrNm(), savedMbr);

    }

    @Test
    public void ID로검색테스트(){
        Mbr m = mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo(202301151234L)
                .mbrId("testId")
                .build();
        mbrRepository.save(m);
//        mbr findMbr = mbrRepository.findById(savedMbr.getMbrId());

//        Assertions.assertEquals(findMbr.getMbrId(), savedMbr.getMbrId());
    }


}


