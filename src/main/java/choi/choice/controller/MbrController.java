package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import choi.choice.service.LoginService;
import choi.choice.service.MemberService;
import choi.choice.service.SessionManager;
import com.mysql.cj.Session;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping("/mbr")
@RequiredArgsConstructor
public class MbrController {

    private final MemberService memberService;
    private final MbrRepository mbrRepository;
    private final LoginService loginService;

    private final SessionManager sessionManager;

    //회원가입
    @GetMapping("register")
    public String register() {
        return "theme/register";
    }

    @PostMapping(value = "register/add")
    public String mbrJoin(@ModelAttribute Mbr mbr, BindingResult bindingResult) throws NoSuchAlgorithmException {
//        Mbr joinedMbr = mbrRepository.findByEmail(mbr.getMbrEmail());
//        if (joinedMbr != null) {
//            bindingResult.reject("RegistFail", "이미 가입된 이메일입니다.");
//            return "redirect:/register/add";
//        }
//
//        Long tempNo = System.currentTimeMillis();
//        mbr.setMbrNo(tempNo);
        memberService.register(mbr);
        return "redirect:/";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        log.info("login form entered");
        return "theme/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute Mbr mbr, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        log.info("login post={}", mbr.getMbrEmail());
        if (bindingResult.hasErrors()) {
            return "theme/login";
        }

        boolean loginMbr = loginService.login(mbr);
        log.info("login 성공여부={}", loginMbr);

        if (!loginMbr) {
            bindingResult.reject("LoginFail", "이메일과 비밀번호가 맞지 않습니다");
            log.info("로그인실패");
            return "theme/login";
        }

        Cookie idCookie = new Cookie("mbrId", mbr.getMbrId());
        response.addCookie(idCookie);

//        HttpSession session = request.getSession();
//        session.setAttribute("loginMbr", mbr);
        sessionManager.createSession("loginMbr", response);
//        model.addAttribute("mbr", mbr);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
//
//    @GetMapping("findId")
//    public String findId(){
//        return "theme/findId";
//    }
}
