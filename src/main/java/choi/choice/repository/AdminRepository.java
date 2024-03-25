package choi.choice.repository;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;

import java.util.List;

public interface AdminRepository {

    void addStdCtgry(StdCtgry stdCtgry);

    void addDspCtgry(DspCtgry dspCtgry);

    List<StdCtgry> stdCtgryList();

    List<DspCtgry> dspCtgryList();

    void stdCtgryDelete(String stdCtgryNo);

    void dspCtgryDelete(String dspCtgryNo);

    void updateStdCtgry(StdCtgry stdCtgry);

    void updateDspCtgry(DspCtgry dspCtgry);
}
