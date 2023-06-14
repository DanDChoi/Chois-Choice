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
}
