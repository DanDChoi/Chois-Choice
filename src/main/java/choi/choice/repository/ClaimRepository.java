package choi.choice.repository;

import choi.choice.domain.*;
public interface ClaimRepository {

    void addClm(Clm clm, ClmGood clmGood);
    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);
}
