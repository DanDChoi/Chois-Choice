package choi.choice.controller;

import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(MbrController.class)
public class MbrControllerTest {

    @Autowired
    MockMvc mvc;

    private MbrRepository mbrRepository;

    @MockBean
    private MemberService memberService;

    @Test
    public void join(mbr mbr){
        mbr.setMbr_no(1L);
        mbr.setMbr_id("hi");
        mbr.setMbr_nm("홍길동");
        mbr.setMbr_email("test@naver.com");
        mbrRepository.save(mbr);
    }
}
