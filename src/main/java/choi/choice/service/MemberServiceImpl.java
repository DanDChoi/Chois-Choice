package choi.choice.service;

import choi.choice.domain.*;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MbrRepository mbrRepository;

    @Transactional
    @Override
    public void register(@ModelAttribute Mbr mbr) throws NoSuchAlgorithmException {

//        if(!mbrRepository.existsById(Long.parseLong(mbr.getMbrId()))){
//            return mbr;
//        }else {
            //회원번호 생성
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
            String idStr = format.format(date) + timeMillis;
            Long no = Long.parseLong(idStr);

            //비밀번호 암호화
            String pwd = encrypt(mbr.getMbrPwd());

            mbr.setMbrNo(no);
            mbr.setMbrId(mbr.getMbrId());
            log.info("mbrNo={},mbrEmail={}", mbr.getMbrNo(),mbr.getMbrEmail());
            mbr.setMbrEmail(mbr.getMbrEmail());
            mbr.setMbrPwd(pwd);
            mbr.setMbrBrthdy(mbr.getMbrBrthdy());
            mbr.setMbrSex(mbr.getMbrSex());
            mbr.setMbrStatCd("ACTIVE");
            mbr.setRegDt(date);
            mbr.setUdtDt(date);
            mbr.setJoinDate(format.format(date));
            log.info("mbrNo={}, mbrId={}, mbrEmail={}, mbrBrthdy={}", mbr.getMbrNo(), mbr.getMbrId(), mbr.getMbrEmail(), mbr.getMbrBrthdy());
            mbrRepository.save(mbr);

            MbrHist mbrHist = null;

            mbrHist.setHistDt(date);
            mbrHist.setMbrId(mbr.getMbrId());
            mbrHist.setMbrNo(no);
            mbrHist.setMbrPwd(pwd);
            mbrHist.setMbrBrthdy(mbr.getMbrBrthdy());
            mbrHist.setMbrSex(mbr.getMbrSex());
            mbrHist.setMbrStatCd("ACTIVE");
            mbrHist.setRegDt(date);
            mbrHist.setUdtDt(date);
            mbrHist.setJoinDate(format.format(date));
            mbrHist.setMbrEmail(mbr.getMbrEmail());
            mbrRepository.saveMbrHist(mbrHist);

            Date acmsltSmonBegDt = getFirstDayOfMonth(-1);
            Date acmsltSmonEndDt = getLastDayOfPreviousMonth();
            Date grdBegDt = getFirstDayOfMonth(0);
            Date grdEndDt = getLastDayOfMonth(0);

            MbrGrd mbrGrd = new MbrGrd(
                    no
                    , "WELCOME"
                    , date
                    , grdBegDt
                    , grdEndDt
                    , acmsltSmonBegDt
                    , acmsltSmonEndDt
                    , "SYSADMIN"
                    , date
                    , "SYSADMIN"
                    , date
            );
            mbrRepository.saveGrd(mbrGrd);
//            MbrGrdHist mbrGrdHist = new MbrGrdHist(no, date, "WELCOME", format.format(date), "2999-12-31", "SYSADMIN", date, "SYSADMIN", date);
//            mbrRepository.saveGrdHist(mbrGrdHist);
//        }
    }

    @Override
    @Transactional
    public Mbr findById(String id) {
        return mbrRepository.findById(id);
    }

    @Override
    public Mbr findByEmail(String email) {
        return mbrRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public MbrGrd findGrdByNo(Long mbrNo) {
        return mbrRepository.findGrdByNo(mbrNo);
    }

    @Override
    public List<Mbr> findAll() {
        List<Mbr> mbr = mbrRepository.findAll(Sort.by(Sort.Direction.DESC, "reg_dt"));
        return mbr;
    }

    @Override
    public void updateMbr(Mbr mbr) {
        mbrRepository.updateMbr(mbr);
    }

    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    @Override
    public List<MbrCpn> findMbrCpns(Long mbrNo) {
        return mbrRepository.mbrIsuCpn(mbrNo);
    }

    @Override
    public List<GoodReview> findAllReviews(Long mbrNo) {
        return mbrRepository.findAllReviews(mbrNo);
    }

    @Override
    public void addMtm(Mbr mbr, CsoMtmInq csoMtmInq, HttpServletRequest request) {
        mbrRepository.addMtm(mbr, csoMtmInq);
    }

    public static Date getFirstDayOfMonth(int offsetMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, offsetMonth);
        return cal.getTime();
    }

    public static Date getLastDayOfPreviousMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(int offsetMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, offsetMonth);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
}

