package choi.choice.service;

import choi.choice.domain.mbr;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface MemberService {

    String login(mbr mbr, HttpServletRequest request, HttpSession session);
    mbr register(mbr mbr) throws NoSuchAlgorithmException;

    Optional<mbr> findById(Long aLong);

    void withdraw(Long id);

    long count(mbr mbr);

//    boolean existById(Long id);
}
