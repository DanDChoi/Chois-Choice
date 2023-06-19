package choi.choice.service;

import choi.choice.domain.Bsk;
import choi.choice.repository.BasketRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;
    private final SessionManager sessionManager;

    @Override
    public void add(Bsk bsk, HttpServletRequest request) {
        Long mbrNo = sessionManager.getSession(request).getMbrNo();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String bskNo = "B" + format.format(date) + timeMillis;

        bsk.setBskNo(bskNo);
        bsk.setMbrNo(mbrNo);
        basketRepository.add(bsk);
    }

    @Override
    public void delete(String bskNo) {
        basketRepository.delete(bskNo);
    }

    @Override
    public Bsk findAll(HttpServletRequest request) {
        Long mbrNo = sessionManager.getSession(request).getMbrNo();
        return basketRepository.findAll(mbrNo);
    }
}
