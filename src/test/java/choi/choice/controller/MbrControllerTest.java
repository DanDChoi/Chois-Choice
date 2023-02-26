package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MbrController.class)
public class MbrControllerTest {

    @Autowired
    MockMvc mvc;

    private MbrRepository mbrRepository;

    @MockBean
    private MemberService memberService;

    @Test
    public void join(Mbr mbr){
        mbrRepository.save(mbr);
    }
}
