package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
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
    MbrBlcklst findBlcklstByNo(Long mbrNo);

    void updateMbr(Mbr mbr);

    void addMtm(Mbr mbr, CsoMtmInq csoMtmInq, HttpServletRequest request);

    void addBlcklst(Mbr mbr);

    void removeBlcklst(Mbr mbr);

    List<Mbr> findBlcklstList();

    void insertMbrLoginLog(MbrLoginLog mbrLoginLog);
}
