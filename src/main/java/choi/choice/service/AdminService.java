package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {

    void addStdCtgry(StdCtgry stdCtgry);

    void addDspCtgry(DspCtgry dspCtgry);

    List<StdCtgry> stdCtgryList();

    List<DspCtgry> dspCtgryList();

    void stdCtgryDelete(String stdCtgryNo);

    void dspCtgryDelete(String dspCtgryNo);

    void updateStdCtgry(StdCtgry stdCtgry, HttpServletRequest request);

    void updateDspCtgry(DspCtgry dspCtgry, HttpServletRequest request);
}
