package choi.choice.repository;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;

public interface AdminRepository {

    void addStdCtgry(StdCtgry stdCtgry);

    void addDspCtgry(DspCtgry dspCtgry);
}
