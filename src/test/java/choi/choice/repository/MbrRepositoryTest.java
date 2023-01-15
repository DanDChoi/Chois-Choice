package choi.choice.repository;

import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MbrRepositoryTest {

    @Autowired
    MbrRepository mbrRepository;

    @AfterAll
    public void clean_up(){
        mbrRepository.deleteAll();
    }

    @Test
    public void 가입(){
        mbr m = mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo("M202301151234")
                .build();

        mbr savedMbr = mbrRepository.save(m);

        Assertions.assertEquals(m.getMbrNm(), savedMbr.getMbrNm());
    }
}
