package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.repository.MbrRepository;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MbrRepository mbrRepository;
    private final SessionManager sessionManager;
    private final GoodService goodService;
    private final OrderService orderService;
    private final MemberService memberService;
    private IdGenService idGenService;

    @Value("${base.device.mobile")
    private String mobileDevice;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@SessionAttribute(value = "loginMember", required = false) Mbr loginMember, Model model, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        String userAgent = request.getHeader("user-agent").toLowerCase();
        String[] deviceStr = StringUtils.split(mobileDevice, "|");
        boolean isPc = true;
        String returnUrl = "";

        for (String mobile : deviceStr) {
            if (userAgent.contains(mobile)) {
                isPc = false;
                break;
            }
        }

        if (isPc) {
//            returnUrl = URL생성필요
        } else {
//            returnUrl =
        }

        if (userAgent.contains("app")) {
            systemPK.setApp("Y");
        } else {
            systemPK.setApp("N");
        }

        log.info("homecontroller 진입");
        if (loginMember == null) {
            log.info("loginMember If 진입 ={} ", loginMember);
            return "index";
        }
        log.info("loginMember if 미진입 ={}", loginMember);
        model.addAttribute("member", loginMember);
        return "index";
    }

    @RequestMapping(value = "/men", method = RequestMethod.GET)
    public String menCate(Model model, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Good> menGoods = goodService.findGoodsByCate("men");
        model.addAttribute("menGoods", menGoods);
        return "menList";
    }

    @RequestMapping(value = "/women", method = RequestMethod.GET)
    public String womenCate(Model model, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Good> womenGoods = goodService.findGoodsByCate("women");
        model.addAttribute("menGoods", womenGoods);
        return "womenList";
    }

    @RequestMapping(value = "/life", method = RequestMethod.GET)
    public String lifeCate(Model model, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<Good> lifeGoods = goodService.findGoodsByCate("life");
        model.addAttribute("menGoods", lifeGoods);
        return "lifeList";
    }

    @RequestMapping(value = "/best", method = RequestMethod.GET)
    public String best(Model model, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        List<OrdGood> bestGoods = orderService.findBestGoods();
        model.addAttribute("bestGoods", bestGoods);
        return "bestList";
    }

    @RequestMapping(value = "/mtm", method = RequestMethod.GET)
    public String mtm(Model model, HttpServletRequest request) {
        return "mtm";
    }

    @RequestMapping(value = "/mtm", method = RequestMethod.POST)
    public void addMtm(Mbr mbr, Model model, @ModelAttribute CsoMtmInq csoMtmInq, HttpServletRequest request){
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String csoSn = format.format(date) + timeMillis;
        String loginId = sessionManager.getSession(request).getMbrId();

        csoMtmInq.setMbrNo(mbr.getMbrNo());
        csoMtmInq.setMtmSn(csoSn);
        csoMtmInq.setAnsStatCd("N");
        csoMtmInq.setInqDt(date);
        csoMtmInq.setRegtrId(loginId);
        csoMtmInq.setRegDt(date);
        csoMtmInq.setUdterId(loginId);
        csoMtmInq.setUdtDt(date);

        memberService.addMtm(mbr, csoMtmInq, request);
    }
}
