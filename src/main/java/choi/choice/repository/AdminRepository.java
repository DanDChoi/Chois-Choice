package choi.choice.repository;

import choi.choice.domain.*;

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

    void addStdDspCtgryCnnc(StdDspCtgryCnnc stdDspCtgryCnnc);

    int stdDspCtgryDupChk(String stdCtgryNo, String dspCtgryNo);

    void stdDspCtgrtCnncDelete(StdDspCtgryCnnc stdDspCtgryCnnc, StdCtgry stdCtgry, DspCtgry dspCtgry);

    List<SysWordDic> getSysWordDicList();

    void addSysCd(SysCd sysCd);
}
