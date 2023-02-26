package choi.choice.service;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MbrRepository mbrRepository;

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

