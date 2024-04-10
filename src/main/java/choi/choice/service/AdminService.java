package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;
import choi.choice.domain.StdDspCtgryCnnc;
import choi.choice.domain.SysWordDic;

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

    void cnncStdDspCtgry(StdCtgry stdCtgry, List<DspCtgry> dspCtgry, HttpServletRequest request);

    void stdDspCtgrtCnncDelete(StdDspCtgryCnnc stdDspCtgryCnnc, StdCtgry stdCtgry, DspCtgry dspCtgry);

    List<SysWordDic> getSysWordDicList();
}
