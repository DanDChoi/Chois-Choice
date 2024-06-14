package choi.choice.repository;

import choi.choice.domain.*;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface MbrRepository {

    void save(Mbr mbr);

//    void saveMbrHist(MbrHist mbrHist);
    void saveGrd(MbrGrd mbrGrd);

//    void saveGrdHist(MbrGrdHist mbrGrdHist);

    void updateMbr(Mbr mbr);
    Mbr findById(String id);

    Mbr findByEmail(String email);
    List<Mbr> findAll(Sort regDt);

    MbrGrd findGrdByNo(Long mbrNo);

    void deleteById(String id);

    Boolean existMbr(String id);

    List<Mbr> findMbrByGrd(String grd);

    List<MbrCpn> mbrIsuCpn(Long mbrNo);

    List<GoodReview> findAllReviews(Long mbrNo);

    void addMtm(Mbr mbr, CsoMtmInq csoMtmInq);

    void saveMbrLoginLog(MbrLoginLog mbrLoginLog);

    void addLoginFailrCount(Mbr mbr);

    Integer getLoginFailrCount(Mbr mbr);

    void loginFailrCountReset(Mbr mbr, Integer count);
}
