package choi.choice.repository;

import choi.choice.domain.Bsk;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class BasketJpaRepository implements BasketRepository{
    @Override
    public void add(Bsk bsk) {

    }

    @Override
    public void delete(String bskNo) {

    }

    @Override
    public Bsk findByNo(String bskNo) {
        return null;
    }
}
