package choi.choice.service;

import choi.choice.domain.*;

public interface ClaimService {

    Clm findClm(String clmNo);

    void editClm(Clm clm, ClmGood clmGood);
}
