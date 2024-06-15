package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.repository.GoodRepository;
import choi.choice.repository.MbrRepository;
import choi.choice.repository.OrderRepository;
import choi.choice.service.*;
import com.mysql.cj.Session;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.naming.Binding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mbr")
@RequiredArgsConstructor
public class MbrController {

    private final MemberService memberService;
    private final MbrRepository mbrRepository;
    private final LoginService loginService;
    private final OrderService orderService;
    private final GoodService goodService;
    private final CouponService couponService;
    private final GoodRepository goodRepository;
    private final SessionManager sessionManager;

    //회원가입
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register/add", method = RequestMethod.POST)
    public String mbrJoin(@ModelAttribute Mbr mbr) throws NoSuchAlgorithmException {
        memberService.register(mbr);
        return "redirect:/";
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loginForm() {
        log.info("login form entered");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Mbr mbr, BindingResult bindingResult, HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
        log.info("login post={}", mbr.getMbrEmail());
        if (bindingResult.hasErrors()) {
            return "login";
        }

        int loginFailrCount = loginService.getLoginFailrCount(mbr);

        if(loginFailrCount >= 5) {
            bindingResult.reject("LoginFail", "로그인 5회 실패하여 로그인 할 수 없습니다. 관리자에게 문의하세요");
            log.info("로그인 횟수 초과 실패");
            return "login";
        }

        log.info("loginService login ={}", loginService.login(mbr));

        if (!loginService.login(mbr)) {
            loginService.addLoginFailrCount(mbr);
            bindingResult.reject("LoginFail", "이메일과 비밀번호가 맞지 않습니다");
            log.info("로그인실패");
            return "login";
        }

        //로그인 실패횟수 초기화
        loginService.loginFailrCountReset(mbr, 0);

        Mbr findMbr = memberService.findByEmail(mbr.getMbrEmail());
        MbrGrd findMbrGrd = memberService.findGrdByNo(findMbr.getMbrNo());

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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(HttpServletRequest request) {
        String mbrId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(mbrId);

        //admin mbrId 확인
        if (mbr.getMbrId() != "admin") {
            return "redirect:/";
        }

        return "admin-page";
    }
//
//    @GetMapping("findId")
//    public String findId(){
//        return "theme/findId";
//    }
    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String profile(Model model, String id, HttpServletRequest request){
        HttpSession session = request.getSession();

        if (session.getAttribute("loginMbr") == null) {
            return "redirect:/";
        }

        Mbr mbr = mbrRepository.findById(id);
        List<Ord> ords = orderService.findOrdsByMbrNo(mbr.getMbrNo());
        List<Cpn> cpns = couponService.mbrCpnList(mbr.getMbrNo());
        List<Good> bskGoods = goodService.findBskGoods(mbr.getMbrNo());
        model.addAttribute("mbr", mbr);
        model.addAttribute("ords", ords);
        model.addAttribute("cpns", cpns);
        model.addAttribute("bskGoods", bskGoods);
        return "user-profile";
    }

    @RequestMapping(value = "/ordList", method = RequestMethod.GET)
    public String ordList(Model model, Mbr mbr) {
        List<Ord> allOrds = orderService.findOrdsByMbrNo(mbr.getMbrNo());
        List<Ord> cnclOrds = new ArrayList<>();

        for (int i = 0; i < allOrds.size(); i++) {
            if (allOrds.get(i).getOrdStatus().equals("CNCL")) {
                cnclOrds.add(allOrds.get(i));
            }
        }
        model.addAttribute("mbr", mbr);
        model.addAttribute("allOrds", allOrds);
        model.addAttribute("cnclOrds", cnclOrds);
        return "ord-list";
    }

    @RequestMapping(value = "/cpnList", method = RequestMethod.GET)
    public String cpnList(Model model, Mbr mbr){
        List<MbrCpn> cpns = mbrRepository.mbrIsuCpn(mbr.getMbrNo());
        model.addAttribute("mbr", mbr);
        model.addAttribute("cpns", cpns);
        return "user-cpnList";
    }

    @RequestMapping(value = "/reviewList", method = RequestMethod.GET)
    public String reviewList(Model model, Mbr mbr) {
        List<GoodReview> reviews = memberService.findAllReviews(mbr.getMbrNo());
        model.addAttribute("reviews", reviews);
        model.addAttribute("mbr", mbr);
        return "user-reviews";
    }

    @RequestMapping(value = "/addGoodReview.popup", method = RequestMethod.GET)
    public String addGoodReviewPop (MultipartHttpServletRequest request, Model model, GoodReview review) throws Exception{
        return "/addGoodReviewForm";
    }

    @RequestMapping(value = "/bsk", method = RequestMethod.GET)
    public String bsk(Mbr mbr, Bsk bsk, Model model){
        List<Good> bskGoods = goodService.findBskGoods(mbr.getMbrNo());
        model.addAttribute("mbr", mbr);
        model.addAttribute("bskGoods", bskGoods);
        return "/bsk";
    }

    @RequestMapping(value = "/bukmkList", method = RequestMethod.GET)
    public String bukmkList(Model model, Mbr mbr, HttpServletRequest request) {
        int bukmkCnt = goodRepository.getBukmkCnt(mbr);
        List<Good> bukmkGoods = goodService.findMbrBukmkGoods(mbr);

        model.addAttribute("mbr", mbr);
        model.addAttribute("bukmkCnt", bukmkCnt);
        model.addAttribute("bukmkGoods", bukmkGoods);
        return "bukmkList";
    }
}
