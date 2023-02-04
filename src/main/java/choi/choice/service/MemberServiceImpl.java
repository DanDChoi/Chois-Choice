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
       Optional<mbr> m = mbrRepository.findByStringId(mbr.getMbrId());
        if (m.isPresent()){
            try{
                // 세션값 설정
                session.setAttribute("user_id", mbr.getMbrId());
                session.setAttribute("user_name", mbr.getMbrNm());
                session.setMaxInactiveInterval(30*60);

            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            return "redirect:/";
        }

        return "ok";
    }

    @Override
    public mbr register(@ModelAttribute mbr mbr) {

        if(!mbrRepository.existsById(Long.parseLong(mbr.getMbrId()))){
            return mbr;
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String timeMillis = Long.toString(System.currentTimeMillis()).substring(0, 8);
            String idStr = format.format(date) + timeMillis;
            Long no = Long.parseLong(idStr);

            mbr.setMbrId(mbr.getMbrId());
            mbr.setMbrNo(no);
            mbr.setMbrEmail(mbr.getMbrEmail());
            mbr.setMbrPwd(mbr.getMbrPwd());
            mbr.setMbrBrthdy(mbr.getMbrBrthdy());
            mbr.setMbrSex(mbr.getMbrSex());
            mbr.setMbrStatCd("ACTIVE");
            return mbrRepository.save(mbr);
        }
    }

    @Override
    public Optional<mbr> findStringId(@ModelAttribute mbr mbr){
        return mbrRepository.findByStringId(mbr.getMbrId());
    }



    @Override
    public void withdraw(Long id){
        mbrRepository.deleteById(id);
    }
}

