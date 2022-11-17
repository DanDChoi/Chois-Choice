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
}
