package choi.choice.service;

import choi.choice.domain.Bsk;

import javax.servlet.http.HttpServletRequest;

public interface BasketService {

    void add(Bsk bsk, HttpServletRequest request);

    void delete(String bskNo);

    Bsk findByNo(String bskNo);
}
