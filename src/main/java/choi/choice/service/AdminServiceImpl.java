package choi.choice.service;

import choi.choice.domain.DspCtgry;
import choi.choice.domain.StdCtgry;
import choi.choice.repository.AdminRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

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
}
