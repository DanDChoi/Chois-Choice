package choi.choice.service;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrGrd;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface MemberService {

    void register(Mbr mbr) throws NoSuchAlgorithmException;
    Mbr findById (String id);

    Mbr findByEmail(String email);

    MbrGrd findGrdByNo(Long mbrNo);

}
