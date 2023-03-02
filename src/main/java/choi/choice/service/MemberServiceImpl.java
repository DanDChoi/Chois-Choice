package choi.choice.service;

import choi.choice.domain.Mbr;
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
            String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 8);
            String idStr = format.format(date) + timeMillis;
            Long no = Long.parseLong(idStr);

            //비밀번호 암호화
            String pwd = encrypt(mbr.getMbrPwd());

            mbr.setMbrId(mbr.getMbrId());
            mbr.setMbrNo(no);
            mbr.setMbrEmail(mbr.getMbrEmail());
            mbr.setMbrPwd(pwd);
            mbr.setMbrBrthdy(mbr.getMbrBrthdy());
            mbr.setMbrSex(mbr.getMbrSex());
            mbr.setMbrStatCd("ACTIVE");
            log.info("mbrNo={}, mbrId={}, mbrEmail={}, mbrBrthdy={}", mbr.getMbrNo(), mbr.getMbrId(), mbr.getMbrEmail(), mbr.getMbrBrthdy());
            mbrRepository.save(mbr);
//        }
    }

    @Override
    public Optional<Mbr> findById(String id) {
        return mbrRepository.findById(id);
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

