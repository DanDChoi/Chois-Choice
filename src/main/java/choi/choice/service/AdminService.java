package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;

import java.util.List;

public interface AdminService {

    void addStdCtgry();

    void addDspCtgry();

    List<StdCtgry> stdCtgryList();

    List<DspCtgry> dspCtgryList();


}
