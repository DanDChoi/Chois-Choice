package choi.choice.repository;

import choi.choice.domain.*;

import java.util.List;

public interface ClaimRepository {

    void addClm(Clm clm, ClmGood clmGood);
    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);

    List<Clm> clmListByMbrNo(String mbrNo);
}
