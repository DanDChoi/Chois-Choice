package choi.choice.service;

import choi.choice.domain.*;
import choi.choice.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService{

    private final ClaimRepository claimRepository;

    @Override
        public void createClm(Ord ord, Clm clm, OrdGood ordGood) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
            String clmNo = "C" + format.format(date) + timeMillis;
            String clmPayNo = "P" + format.format(date) + timeMillis;

            String regtr = sessionManager.getSession(request).getMbrId();

            Pay refndPay = null;
            Pay OriginPay = payService.findPayByOrdNo(ord.getOrdNo());

            refndPay.setPayNo(clmPayNo);
            refndPay.setPayAmt(ordGood.getSalePrc());
            refndPay.setRegtrId(regtr);
            refndPay.setUdterId(regtr);
            refndPay.setRegDt(date);
            refndPay.setUdtDt(date);
            if(clm.getClmTpCd().equals("EXCHG")){
                refndPay.setDealTpCd("EXCHG");
            } else if (clm.getClmTpCd().equals("REFND")) {
                refndPay.setDealTpCd("REFND");
            }

            payService.addClmPay(refndPay, OriginPay.getPayNo());

            clm.setClmNo(clmNo);
            clm.setGodNo(ordGood.getGoodNo());
            clm.setOrdNo(ord.getOrdNo());
            clm.setMbrNo(ord.getMbrNo());
            clm.setRegtrId(regtr);
            clm.setRegDt(date);
            clm.setUdterId(regtr);
            clm.setUdtDt(date);

            ClmGood clmGood = null;

            clmGood.setClmGoodTurn(ordGood.getOrdGoodTurn());
            clmGood.setClmNo(clmNo);
            clmGood.setGoodNm(ordGood.getGoodNm());
            clmGood.setGoodHistNo(ordGood.getGoodHistNo());
            clmGood.setRegtrId(regtr);
            clmGood.setRegDt(date);
            clmGood.setUdterId(regtr);
            clmGood.setUdtDt(date);

            claimRepository.addClm(clm, clmGood);
        }

    @Override
    public Clm findClm(String clmNo) {
        Clm clm = claimRepository.findClm(clmNo);
        return clm;
    }

    @Override
    public void editClm(Clm clm, ClmGood clmGood) {
        clmGood.setGoodHistNo(clmGood.getGoodHistNo());
        claimRepository.editClm(clm, clmGood);
    }

    @Override
    public List<Clm> clmListByMbrNo(String mbrNo) {
        List<Clm> clms = claimRepository.clmListByMbrNo(mbrNo);
        return clms;
    }

    @Override
    public List<Clm> clmListByOrdNo(String ordNo) {
        List<Clm> clms = claimRepository.clmListByOrdNo(ordNo);
        return clms;
    }
}
