package choi.choice.repository;

import choi.choice.domain.*;
public interface ClaimRepository {

    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);
}
