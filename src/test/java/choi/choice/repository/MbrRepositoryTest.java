package choi.choice.repository;

import choi.choice.domain.Mbr;
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
        Mbr m = Mbr.builder()
                .mbrEmail("test@naver.com")
                .mbrNm("홍길동")
                .mbrNo(202301151234L)
                .build();

        mbrRepository.save(m);

        Assertions.assertEquals(m.getMbrNm(), mbrRepository.findByEmail("test@naver.com"));
    }
}
