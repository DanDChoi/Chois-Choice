package choi.choice.controller;

import choi.choice.domain.mbr;
import choi.choice.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/mbr")
@RequiredArgsConstructor
public class MbrController {

    @Autowired
    private MemberService memberService;

    //회원가입
    @GetMapping("register")
    public ResponseEntity<mbr> register(mbr mbr){
        return new ResponseEntity<mbr>(memberService.register(mbr), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<mbr> mbrJoin(@ModelAttribute mbr mbr, Model model, HttpServletRequest request) {
        return new ResponseEntity<mbr>(memberService.register(mbr), HttpStatus.OK);
    }

    @GetMapping("login")
    public String loginForm(){
        return "theme/login";
    }

    @PostMapping("login")
    public void login(@ModelAttribute mbr mbr, HttpServletRequest request) {

    }

    @GetMapping("findId")
    public void findId(){

    }
    @GetMapping("findPw")
    public void findPw(){

    }
}
