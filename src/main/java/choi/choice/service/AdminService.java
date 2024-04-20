package choi.choice.service;

import choi.choice.domain.*;

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

    void addSysCd(SysCd sysCd, HttpServletRequest request);

    SysCd getSysCdDetail(String cd);

    void sysCdUpdate(SysCd sysCd, HttpServletRequest request);
}
