package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdDspCtgryCnnc;
import choi.choice.domain.StdCtgry;
import choi.choice.repository.AdminRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final SessionManager sessionManager;

    @Override
    public void addStdCtgry(StdCtgry stdCtgry) {
        adminRepository.addStdCtgry(stdCtgry);
    }

    @Override
    public void addDspCtgry(DspCtgry dspCtgry) {
        adminRepository.addDspCtgry(dspCtgry);
    }

    @Override
    public List<StdCtgry> stdCtgryList() {
        return adminRepository.stdCtgryList();
    }

    @Override
    public List<DspCtgry> dspCtgryList() {
        return adminRepository.dspCtgryList();
    }

    @Override
    public void stdCtgryDelete(String stdCtgryNo) {
        adminRepository.stdCtgryDelete(stdCtgryNo);
    }

    @Override
    public void dspCtgryDelete(String dspCtgryNo) {
        adminRepository.dspCtgryDelete(dspCtgryNo);
    }

    @Override
    public void updateStdCtgry(StdCtgry stdCtgry, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        format.format(date);

        String udter = sessionManager.getSession(request).getMbrId();

        stdCtgry.setUdtDt(date);
        stdCtgry.setUdterId(udter);

        adminRepository.updateStdCtgry(stdCtgry);
    }

    @Override
    public void updateDspCtgry(DspCtgry dspCtgry, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        format.format(date);

        String udter = sessionManager.getSession(request).getMbrId();

        dspCtgry.setUdtDt(date);
        dspCtgry.setUdterId(udter);

        adminRepository.updateDspCtgry(dspCtgry);
    }

    @Override
    public void cnncStdDspCtgry(StdCtgry stdCtgry, List<DspCtgry> dspCtgry, HttpServletRequest request) {

        String msg = "";
        StdDspCtgryCnnc stdDspCtgryCnnc = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        format.format(date);
        String loginId = sessionManager.getSession(request).getMbrId();

        if (dspCtgry.size() > 0) {
            for (int i=0; i< dspCtgry.size(); i++) {
                //중복체크
                int dupCnt = adminRepository.stdDspCtgryDupChk(stdCtgry.getStdCtgryNo(), dspCtgry.get(i).getDspCtgryNo());

                if(dupCnt == 0) {
                    stdDspCtgryCnnc.setDspCtgryNo(dspCtgry.get(i).getDspCtgryNo());
                    stdDspCtgryCnnc.setStdCtgryNo(stdCtgry.getStdCtgryNo());
                    stdDspCtgryCnnc.setUseYn("Y");
                    stdDspCtgryCnnc.setRegtrId(loginId);
                    stdDspCtgryCnnc.setUdterId(loginId);
                    stdDspCtgryCnnc.setRegDt(date);
                    stdDspCtgryCnnc.setUdtDt(date);

                    adminRepository.addStdDspCtgryCnnc(stdDspCtgryCnnc);
                }
            }
         }

    }
}
