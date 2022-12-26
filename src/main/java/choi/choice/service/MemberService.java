package choi.choice.service;

import choi.choice.domain.mbr;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {

    mbr login(mbr mbr, HttpServletRequest request);
    mbr register(mbr mbr);
}
