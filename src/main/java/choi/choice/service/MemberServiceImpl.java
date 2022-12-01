package choi.choice.service;

import choi.choice.domain.mbr;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Override
    public mbr login(mbr mbr) {
        return mbr;
    }

    @Override
    public int join(mbr mbr){
        return 0;
    }

    @Override
    public mbr register(mbr mbr) {
        mbr.getMbr_pwd();
        String mbr_email = mbr.getMbr_email();
        return null;
    }
}

