package choi.choice.repository;

import choi.choice.domain.Bsk;

public interface BasketRepository {

    void add(Bsk bsk);

    void delete(String bskNo);

    Bsk findByNo(String bskNo);
}
