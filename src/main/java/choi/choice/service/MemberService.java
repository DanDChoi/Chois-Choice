package choi.choice.service;

import choi.choice.domain.GoodReview;
import choi.choice.domain.Mbr;
import choi.choice.domain.MbrCpn;
import choi.choice.domain.MbrGrd;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    void register(Mbr mbr) throws NoSuchAlgorithmException;
    Mbr findById (String id);
    Mbr findByEmail(String email);
    MbrGrd findGrdByNo(Long mbrNo);
    List<Mbr> findAll();
    List<MbrCpn> findMbrCpns(Long mbrNo);
    List<GoodReview> findAllReviews(Long mbrNo);

}
