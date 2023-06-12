package choi.choice.service;

import choi.choice.domain.Ord;
import choi.choice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final SessionManager sessionManager;

    @Override
    public void createOrd(@ModelAttribute Ord ord, HttpServletRequest request) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = new Date();

        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
        String ordNo = "O" + format.format(date) + timeMillis;

        String regtr = sessionManager.getSession(request).getMbrId();

        ord.setOrdNo(ordNo);
        ord.setOrdDt(format.format(date));
        ord.setRegtrId(regtr);
        ord.setRegDt(date);
        ord.setUdterId(regtr);
        ord.setUdtDt(date);

        orderRepository.add(ord);
    }

    @Override
    public Ord findOne(String ordNo) {
        Ord ord = orderRepository.findOne(ordNo);
        return ord;
    }

    @Override
    public List<Ord> findAll() {
        List<Ord> ords = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return ords;
    }

    @Override
    public void deleteOrd(String ordNo) {

    }
}
