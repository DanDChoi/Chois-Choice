package choi.choice.service;

import choi.choice.domain.mbr;
import org.springframework.stereotype.Service;

public interface MemberService {

    mbr login(mbr mbr);
    mbr register(mbr mbr);
}
