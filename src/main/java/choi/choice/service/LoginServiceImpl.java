package choi.choice.service;

import choi.choice.domain.Mbr;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MbrRepository mbrRepository;
    @Override
    public boolean login(Mbr mbr){
        Mbr findMbr = mbrRepository.findByEmail(mbr.getMbrEmail());

        if (findMbr == null) {
            return false;
        }
        if (!findMbr.getMbrPwd().equals(mbr.getMbrPwd())) {
            return false;
        }
        return true;
    }
}
