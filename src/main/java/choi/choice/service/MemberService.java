package choi.choice.service;

import choi.choice.domain.mbr;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface MemberService {

    String login(mbr mbr, HttpServletRequest request, HttpSession session);
    mbr register(mbr mbr);

    Optional<mbr> findById(Long aLong);

    void withdraw(Long id);
}
