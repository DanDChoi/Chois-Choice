package choi.choice.service;

import choi.choice.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClaimService {

    void createClm(Ord ord, Clm clm, OrdGood ordGood, LgsDlivyDrctGood lgsDlivyDrctGood, HttpServletRequest request);
    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);

    List<Clm> clmListByMbrNo(Long mbrNo);

    List<Clm> clmListByOrdNo(String ordNo);
}
