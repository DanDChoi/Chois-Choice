package choi.choice.controller;

import choi.choice.domain.*;
import choi.choice.repository.GoodRepository;
import choi.choice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/good")
@RequiredArgsConstructor
public class ProductsController {

    private final GoodService goodService;
    private final CouponService couponService;
    private final GoodRepository goodRepository;
    private final SessionManager sessionManager;
    private final MemberService memberService;
    private IdGenService idGenService;

    @RequestMapping (value="create", method = RequestMethod.GET)
    public String createGoodForm() {
        return "good/createForm";
    }

    @RequestMapping(value = "create/add", method = RequestMethod.POST)
    public String createGood(@ModelAttribute GoodExtend goodExtend, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        goodService.add(goodExtend, request);
        return "ok";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchGood(String goodNo) {
        goodService.findByNo(goodNo);
        goodService.findGoodItmByNo(goodNo);
        return "ok";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public void deleteGood(String goodNo) {
        GoodHist goodHist = null;
        Good good = goodService.findByNo(goodNo);
        goodHist.setGoodNm(good.getGoodNm());
        goodHist.setGoodNm(good.getGoodNm());
        goodHist.setColorNm(good.getColorNm());
        goodHist.setColorCd(good.getColorCd());
        goodHist.setSaleBegDate(good.getSaleBegDate());
        goodHist.setSaleEndDate(good.getSaleEndDate());
        goodHist.setRegtrId(good.getRegtrId());
        goodHist.setRegDt(good.getRegDt());
        goodHist.setUdterId(good.getUdterId());
        goodHist.setUdtDt(good.getUdtDt());
        goodService.deleteByNo(goodNo, goodHist);
        goodService.deleteGoodItmByNo(goodNo);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detailGood(String goodNo, Model model) {
        Good good = goodService.findByNo(goodNo);
        String period = "0";
        List<Cpn> cpns = couponService.validCpns(period);

        //쿠폰가
        int highestSaleRate = 0;
        int highestSaleAmt = 0;
        for (int i = 0; i < cpns.size(); i++) {
            if (cpns.get(i).getCpnTpCd().equals("RATE")) {
                if (cpns.get(i).getCpnDcRate() > highestSaleRate) {
                    highestSaleRate = cpns.get(i).getCpnDcRate();
                }
            } else if (cpns.get(i).getCpnTpCd().equals("AMT")) {
                if (cpns.get(i).getCpnDcAmt() > highestSaleAmt) {
                    highestSaleAmt = cpns.get(i).getCpnDcAmt();
                }
            }
        }

        if (highestSaleRate > 0) {
            int lastRatePrc = (100 - highestSaleRate) / 100 * Integer.parseInt(good.getGoodPrc());
        }
        if (highestSaleAmt > 0) {
            int lastAmtPrc = Integer.parseInt(good.getGoodPrc()) - highestSaleAmt;
        }

        int lastPrc = highestSaleAmt > highestSaleRate ? highestSaleAmt : highestSaleRate;

        good.setGoodPrc(String.valueOf(lastPrc));

        //상품리뷰
        List<GoodReview> reviews = goodService.findRvByNo(goodNo);

        model.addAttribute("good", good);
        model.addAttribute("review", reviews);
        model.addAttribute("cpn", cpns);
        return "good/detail?goodNo=" + goodNo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGoodForm(@ModelAttribute Good good, HttpServletRequest request, Model model) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        return "good/detail?goodNo=" + good.getGoodNo() + "&mode=edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateGood(@ModelAttribute Good good, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        String loginId = sessionManager.getSession(request).getMbrId();

        good.setUdterId(loginId);

        goodService.updateGood(good);
    }

    @RequestMapping(value = "/addBukmk", method = RequestMethod.POST)
    public void addBukmk(@ModelAttribute String goodNo, HttpServletRequest request) {
        SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
        Good good = goodService.findByNo(goodNo);
        String loginId = sessionManager.getSession(request).getMbrId();
        Mbr mbr = memberService.findById(loginId);

        goodService.addBukmk(good, mbr);
    }

    @RequestMapping(value = "/insertGoodsReviewAction", method = RequestMethod.POST)
    public void insertGoodsReviewAction(MultipartHttpServletRequest request, Model model, MemberOrdGodFoDTO dto, @RequestParam(value = "file", required = false) MultipartFile[] files) throws Exception {
        SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
        //TODO securityUserDetail DTO 생성
//        SecurityUserDetail
//        dto.setMbr()
        dto.setMallId(pk.getMall());
        dto.getGoodReview().setMbrNo(dto.getMbr().getMbrNo());
        dto.getGoodReview().setGoodNo(dto.getGoodNo());
        dto.getGoodReview().setBestYn("N");

        List<MultipartFile> multipartFiles = null;
        if (files != null && files.length > 0) {
           multipartFiles = Arrays.asList(files);
        }

        OrdGood ordGood = null;

        //TODO
//        insertMyGoodsReview
    }

}
