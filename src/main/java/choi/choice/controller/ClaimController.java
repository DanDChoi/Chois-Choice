package choi.choice.controller;

import choi.choice.domain.Clm;
import choi.choice.domain.ClmGood;
import choi.choice.domain.Ord;
import choi.choice.domain.OrdGood;
import choi.choice.repository.OrderRepository;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/clm")
@RequiredArgsConstructor
public class ClaimController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final SessionManager sessionManager;

    private final MemberService memberService;

    private final ClaimService claimService;

    private final GoodService goodService;

    @RequestMapping(value = "/clmForm", method = RequestMethod.GET)
    public String createClm(Ord ord, OrdGood ordGood, Model model) {
        Ord findOrd = orderService.findOne(ord.getOrdNo());
        List<OrdGood> findOrdGoods = orderService.findOrdGoods(ord.getOrdNo());

        model.addAttribute("ord", findOrd);
        model.addAttribute("ordGoods", findOrdGoods);
        return "clmForm";
    }

    @RequestMapping(value = "/addClm", method = RequestMethod.POST)
    public void createClm(Ord ord, Clm clm, OrdGood ordGood, HttpServletRequest request) {
        claimService.createClm(ord, clm, ordGood, request);
    }

    @RequestMapping(value = "/clmDetail", method = RequestMethod.GET)
    public String clmDetail(Ord ord, ClmGood clmGood, Clm clm, HttpServletRequest request, Model model) {
        Clm clmDetail = claimService.findClm(clm.getClmNo());
        model.addAttribute("clm", clmDetail);
        return "clmDetail/" + clm.getClmNo();
    }

    @RequestMapping(value = "/editClm", method = RequestMethod.POST)
    public void editClm(Clm clm, ClmGood clmGood, Model model) {
        claimService.editClm(clm, clmGood);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Clm> clmListByMbrNo(Long mbrNo, String ordNo) {
        List<Clm> clmList = null;
        if (mbrNo != null && !mbrNo.equals("")){
            clmList = claimService.clmListByMbrNo(mbrNo);
        } else if (ordNo != null && !ordNo.equals("")){
            clmList = claimService.clmListByOrdNo(ordNo);
        }
        return clmList;
    }
}
