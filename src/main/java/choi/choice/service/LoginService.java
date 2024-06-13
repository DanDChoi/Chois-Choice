package choi.choice.service;

import choi.choice.domain.Mbr;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public interface LoginService {

    boolean login(Mbr mbr) throws NoSuchAlgorithmException;

    void logout(HttpServletRequest request);

    void addLoginFailrCount(Mbr mbr);

    Integer getLoginFailrCount(Mbr mbr);
}
