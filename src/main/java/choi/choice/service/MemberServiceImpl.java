package choi.choice.service;

import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MbrRepository mbrRepository;

    @Override
    public String login(@ModelAttribute mbr mbr, HttpServletRequest request, HttpSession session) {
        mbrRepository.findByStringId(mbr.getMbrId());
        try{
            // 세션값 설정
            session.setAttribute("user_id", mbr.getMbrId());
            session.setAttribute("user_name", mbr.getMbrNm());
            session.setMaxInactiveInterval(30*60);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public mbr register(@ModelAttribute mbr mbr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeMillis = Long.toString(System.currentTimeMillis()).substring(0,8);
        String id = "M" + format.format(date) + timeMillis;
        log.info("id난수={}", id);

        mbr.setMbrId(id);
        mbr.setMbrEmail(mbr.getMbrEmail());
        mbr.setMbrPwd(mbr.getMbrPwd());
        return mbrRepository.save(mbr);
    }

    @Override
    public Optional<mbr> findStringId(@ModelAttribute mbr mbr){
        return mbrRepository.findByStringId(mbr.getMbrId());
    }
}

