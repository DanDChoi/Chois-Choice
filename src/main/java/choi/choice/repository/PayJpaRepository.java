package choi.choice.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PayJpaRepository implements PayRepository{
}
