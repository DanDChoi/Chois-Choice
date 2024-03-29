package choi.choice.service;

import choi.choice.domain.*;
import choi.choice.repository.CouponRepository;
import choi.choice.repository.GoodRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService{

    private final GoodRepository goodRepository;

    private final CouponRepository couponRepository;

    private final SessionManager sessionManager;

    private final MemberService memberService;

    @Override
    public void add(@ModelAttribute Good good, HttpServletRequest request) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String goodNo = "G" + format.format(date) + timeMillis;
        String goodItmNo = "I" + format.format(date) + timeMillis;

        String regtr = sessionManager.getSession(request).getMbrId();

        good.setGoodNo(goodNo);
        good.setSaleBegDate(format.format(date));
        good.setRegDt(date);
        good.setRegtrId(regtr);
        good.setUdtDt(date);
        good.setUdterId(regtr);
        good.setSaleEndDate("2999-12-31");

        goodRepository.save(good);

        GoodItm goodItm = null;

        goodItm.setGoodNo(goodNo);
        goodItm.setGoodItmNo(goodItmNo);
        goodItm.setGoodNm(good.getGoodNm());
        goodItm.setSaleBegDate(good.getSaleBegDate());
        goodItm.setSaleEndDate(good.getSaleEndDate());
        goodItm.setRegDt(good.getRegDt());
        goodItm.setRegtrId(good.getRegtrId());
        goodItm.setUdtDt(good.getUdtDt());
        goodItm.setUdterId(good.getUdterId());

        goodRepository.saveItm(goodItm);

        String histNo = "1";
        goodRepository.saveGoodHist(good, histNo);
    }

    @Override
    public Good findByNo(String goodNo) {
        try{
            if(goodRepository.existGood(goodNo)){
                return goodRepository.findByNo(goodNo);
            }
        } catch (Exception e){
            throw new NullPointerException();
        }
        return null;
    }

    @Override
       public GoodItm findGoodItmByNo(String goodNo) {
           try{
               if(goodRepository.existGoodItm(goodNo)){
                   return goodRepository.findGoodItmByNo(goodNo);
               }
           } catch (Exception e){
               throw new NullPointerException();
           }
           return null;
       }

    @Override
    public List<Good> findAll(){
        List<Good> goods = goodRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return goods;
    }

    @Override
    public List<GoodReview> findAllReviews() {
        List<GoodReview> reviews = goodRepository.findAllReviews(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return reviews;
    }

    @Override
    public void updateReview(GoodReview review) {
        goodRepository.updateReview(review);
    }

    @Override
    public void deleteReview(String goodNo) {
        goodRepository.deleteReview(goodNo);
    }


    @Override
    public void deleteByNo(String goodNo, GoodHist goodHist){

        String histNo = goodRepository.getGoodHistSeq(goodNo);
        goodHist.setGoodHistNo(histNo);
        goodRepository.insertGoodHist(goodHist);
        goodRepository.deleteByNo(goodNo);
    }

    @Override
    public void deleteGoodItmByNo(String goodNo) {
        goodRepository.deleteGoodItmByNo(goodNo);
    }

    @Override
    public List<GoodReview> findRvByNo(String goodNo) {
        List<GoodReview> review = goodRepository.findRvByNo(goodNo);
        return review;
    }


    @Override
    public List<Good> findGoodsByCate(String cate) {
        return goodRepository.findGoodsByCate(cate);
    }

    @Override
    public List<Good> findBskGoods(Long mbrNo) {
        return goodRepository.findBskGoods(mbrNo);
    }

    @Override
    public void updateGood(Good good) {
        int maxSeq = Integer.parseInt(goodRepository.getGoodHistSeq(good.getGoodNo()));
        String nextSeq = String.valueOf(maxSeq + 1);
        GoodHist goodHist = null;
        goodHist.setGoodHistNo(nextSeq);
        goodHist.setGoodNm(good.getGoodNm());
        goodHist.setStdCtgryNo(good.getStdCtgryNo());
        goodHist.setGoodNo(good.getGoodNo());
        goodHist.setSaleBegDate(good.getSaleBegDate());
        goodHist.setSaleEndDate(good.getSaleEndDate());
        goodHist.setColorNm(good.getColorNm());
        goodHist.setColorCd(good.getColorCd());
        goodHist.setRegtrId(good.getRegtrId());
        goodHist.setRegDt(good.getRegDt());
        goodHist.setUdterId(good.getUdterId());
        goodHist.setUdtDt(good.getUdtDt());

        goodRepository.insertGoodHist(goodHist);

        goodRepository.updateGood(good);
    }

    @Override
    public int addBukmk(Good good, Mbr mbr) {
        int bukmkSn = goodRepository.getBukmkSn(mbr);
        int bukmkCnt = goodRepository.addBukmk(good, mbr, bukmkSn);
        return bukmkCnt;
    }

    @Override
    public List<Good> findMbrBukmkGoods(Mbr mbr) {
        int bukmkCnt = goodRepository.getBukmkCnt(mbr);
        List<Good> bukmkGoods = goodRepository.bukmkGoods(mbr);
        return bukmkGoods;
    }
}
