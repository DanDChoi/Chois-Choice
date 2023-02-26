package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping("/mbr")
@RequiredArgsConstructor
public class MbrController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MbrRepository mbrRepository;

    //회원가입
    @GetMapping("register")
    public String register(){
        return "theme/register";
    }

    @PostMapping(value ="register/add")
    public String mbrJoin(@ModelAttribute Mbr mbr) throws NoSuchAlgorithmException {
//        new ResponseEntity<mbr>(memberService.register(mbr), HttpStatus.OK);
        memberService.register(mbr);
//        mbrRepository.save(mbr);
        return "redirect:/";
    }

    @GetMapping("login")
    public String loginForm(){
        return "theme/login";
    }

//    @PostMapping("login")
//    public void login(@ModelAttribute Mbr mbr, HttpServletRequest request) {
//        request.getSession();
//        memberService.findById(mbr.getMbrNo());
//
//    }
//
//    @GetMapping("findId")
//    public String findId(){
//        return "theme/findId";
//    }
}
