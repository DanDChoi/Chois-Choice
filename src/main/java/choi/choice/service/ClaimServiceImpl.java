package choi.choice.service;

import choi.choice.domain.Clm;
import choi.choice.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService{

    private final ClaimRepository claimRepository;
    @Override
    public Clm findClm(String clmNo) {
        Clm clm = claimRepository.findClm(clmNo);
        return clm;
    }
}