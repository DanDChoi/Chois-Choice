package choi.choice.service;

import choi.choice.domain.Mbr;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface MemberService {

    void register(Mbr mbr) throws NoSuchAlgorithmException;
    Optional<Mbr> findById (String id);

}
