package choi.choice.service;

import choi.choice.domain.mbr;
import choi.choice.repository.MbrRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
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
    public mbr login(@ModelAttribute mbr mbr, HttpServletRequest request) {
        mbrRepository.findByStringId(mbr.getMbrId());
        request.getSession();
        return mbr;
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

