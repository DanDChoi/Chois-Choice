package choi.choice.service;

import choi.choice.domain.Mbr;
import choi.choice.domain.MbrLoginLog;
import choi.choice.repository.MbrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MbrRepository mbrRepository;
    private final SessionManager sessionManager;
    @Override
    @Transactional
    public boolean login(Mbr mbr) throws NoSuchAlgorithmException {
        Mbr findMbr = mbrRepository.findByEmail(mbr.getMbrEmail());

        if (findMbr == null) {
            return false;
        }
        String pwd = encrypt(mbr.getMbrPwd());
        if (!findMbr.getMbrPwd().equals(pwd)) {
            return false;
        }

        MbrLoginLog mbrLoginLog = null;
        Date date = new Date();

        mbrLoginLog.getMbrLoginLogPK().setMbrLoginCd("LOGIN");
        mbrLoginLog.getMbrLoginLogPK().setMbrNo(mbr.getMbrNo());
        mbrLoginLog.getMbrLoginLogPK().setLogOccurDt(date);
//        mbrLoginLog.setLoginNationCd();
//        mbrLoginLog.setLoginIp();
//        mbrLoginLog.setDvcCd();

        mbrRepository.saveMbrLoginLog(mbrLoginLog);

        return true;
    }

    @Override
    public void logout(HttpServletRequest request){
        sessionManager.expire(request);
    }

    public String encrypt(String text) throws NoSuchAlgorithmException {
           MessageDigest md = MessageDigest.getInstance("SHA-256");
           md.update(text.getBytes());

           return bytesToHex(md.digest());
       }

       private String bytesToHex(byte[] bytes) {
           StringBuilder builder = new StringBuilder();
           for (byte b : bytes) {
               builder.append(String.format("%02x", b));
           }
           return builder.toString();
       }
}
