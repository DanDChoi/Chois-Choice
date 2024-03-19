package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;

import java.util.List;

public interface AdminService {

    void addStdCtgry(StdCtgry stdCtgry);

    void addDspCtgry(DspCtgry dspCtgry);

    List<StdCtgry> stdCtgryList();

    List<DspCtgry> dspCtgryList();

    void stdCtgryDelete(String stdCtgryNo);

}
