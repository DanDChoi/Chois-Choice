package choi.choice.controller;


import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MbrTest {

    @Autowired
    private MbrRepository mbrRepository;

    public MbrTest(MbrRepository mbrRepository) {
        this.mbrRepository = mbrRepository;
    }

    @ParameterizedTest
    public void 가입(mbr mbr) {
        mbr.setMbr_no(1L);
        mbr.setMbr_nm("홍길동");
        mbr.setMbr_email("abc@naver.com");
        mbrRepository.save(mbr);
    }

}


