package choi.choice.service;

import choi.choice.domain.mbr;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    @Override
    public mbr login(mbr mbr) {
        return mbr;
    }

    @Override
    public mbr register(mbr mbr) {
        mbr.getMbr_pwd();
        String mbr_email = mbr.getMbr_email();
        log.info(mbr_email);
        return null;
    }
}

