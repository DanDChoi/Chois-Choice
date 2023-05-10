package choi.choice.service;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 6);
            String idStr = format.format(date) + timeMillis;
            Long no = Long.parseLong(idStr);

            //비밀번호 암호화
            String pwd = encrypt(mbr.getMbrPwd());

            mbr.setMbrNo(no);
            log.info("mbrNo={},mbrEmail={}", mbr.getMbrNo(),mbr.getMbrEmail());
            mbr.setMbrEmail(mbr.getMbrEmail());
            mbr.setMbrPwd(pwd);
            mbr.setMbrBrthdy(mbr.getMbrBrthdy());
            mbr.setMbrSex(mbr.getMbrSex());
            mbr.setMbrStatCd("ACTIVE");
            mbr.setJoinDate(format.format(date));
            MbrGrd mbrGrd = new MbrGrd(no, "WELCOME", format.format(date), "2999-12-31", "SYSADMIN");
            log.info("mbrNo={}, mbrId={}, mbrEmail={}, mbrBrthdy={}", mbr.getMbrNo(), mbr.getMbrId(), mbr.getMbrEmail(), mbr.getMbrBrthdy());
            mbrRepository.save(mbr);
            mbrRepository.saveGrd(mbrGrd);
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
}

