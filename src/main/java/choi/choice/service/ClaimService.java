package choi.choice.service;

import choi.choice.domain.*;

public interface ClaimService {

    void createClm(Ord ord, Clm clm, OrdGood ordGood);
    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);
}
