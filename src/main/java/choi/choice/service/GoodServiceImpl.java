package choi.choice.service;

import choi.choice.domain.Good;
import choi.choice.repository.GoodRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService{

    private final GoodRepository goodRepository;

    @Override
    public void add(@ModelAttribute Good good) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 8);
        String godNo = "G" + format.format(date) + timeMillis;

        good.setGoodNo(godNo);
        good.setSaleEndDate(format.format(date));
        good.setSaleEndDate("2999-12-31");

        goodRepository.save(good);
    }

    @Override
    public Good findByNo(String goodNo) {
        return goodRepository.findByNo(goodNo);
    }

    @Override
    public void deleteByNo(String goodNo){
        goodRepository.deleteByNo(goodNo);
    }
}
