package choi.choice.controller;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;
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
import java.lang.reflect.InvocationTargetException;
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
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register/add")
    public String mbrJoin(@ModelAttribute Mbr mbr) throws NoSuchAlgorithmException {
        memberService.register(mbr);
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        log.info("login form entered");
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Mbr mbr, BindingResult bindingResult, HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
        log.info("login post={}", mbr.getMbrEmail());
        if (bindingResult.hasErrors()) {
            return "login";
        }
        log.info("loginService login ={}", loginService.login(mbr));
        if (!loginService.login(mbr)) {
            bindingResult.reject("LoginFail", "이메일과 비밀번호가 맞지 않습니다");
            log.info("로그인실패");
            return "login";
        }

        Mbr findMbr = memberService.findByEmail(mbr.getMbrEmail());
        MbrGrd findMbrGrd = memberService.findGrdByNo(mbr.getMbrNo());

        log.info("findMbr = {}", findMbr.toString());
        HttpSession session = request.getSession();

        session.setAttribute("loginMember", findMbr);
        model.addAttribute("mbr", findMbr);
        model.addAttribute("mbrGrd", findMbrGrd);


//        Cookie idCookie = new Cookie("mbrId", mbr.getMbrId());
//        response.addCookie(idCookie);
//
//        sessionManager.createSession("loginMbr", response);

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

    @GetMapping("/adminPage")
    public String adminPage() {
        return "admin-page";
    }
//
//    @GetMapping("findId")
//    public String findId(){
//        return "theme/findId";
//    }
    @GetMapping("/userProfile")
    public String profile(Model model, String id, HttpServletRequest request){
        HttpSession session = request.getSession();

        if (session.getAttribute("loginMbr") == null) {
            return "redirect:/";
        }

        Mbr mbr = mbrRepository.findById(id);
        model.addAttribute("profile", mbr);
        return "user-profile";
    }
}
