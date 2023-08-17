package choi.choice.service;

import choi.choice.domain.Good;
import choi.choice.domain.GoodReview;
import choi.choice.repository.GoodRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService{

    private final GoodRepository goodRepository;

    private final SessionManager sessionManager;

    @Override
    public void add(@ModelAttribute Good good, HttpServletRequest request) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String godNo = "G" + format.format(date) + timeMillis;

        String regtr = sessionManager.getSession(request).getMbrId();

        good.setGoodNo(godNo);
        good.setSaleBegDate(format.format(date));
        good.setRegDt(date);
        good.setRegtrId(regtr);
        good.setUdtDt(date);
        good.setUdterId(regtr);
        good.setSaleEndDate("2999-12-31");

        goodRepository.save(good);
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
    public List<Good> findAll(){
        List<Good> goods = goodRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return goods;
    }

    @Override
    public void deleteByNo(String goodNo){
        goodRepository.deleteByNo(goodNo);
    }

    @Override
    public GoodReview findRvByNo(String goodNo) {
        GoodReview review = goodRepository.findRvByNo(goodNo);
        return review;
    }
}
