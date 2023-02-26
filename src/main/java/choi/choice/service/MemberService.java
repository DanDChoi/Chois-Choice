package choi.choice.service;

import choi.choice.domain.Mbr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface MemberService {

    void register(Mbr mbr) throws NoSuchAlgorithmException;

}
