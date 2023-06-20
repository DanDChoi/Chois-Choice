package choi.choice.service;

import choi.choice.domain.Bsk;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BasketService {

    void add(Bsk bsk, HttpServletRequest request);

    void delete(String bskNo);

    List<Bsk> findAll(HttpServletRequest request);
}
