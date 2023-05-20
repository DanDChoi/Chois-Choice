package choi.choice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EventJpaRepository implements EventRepository{

    private final EntityManager em;

    @Autowired
    public EventJpaRepository(EntityManager em) {
        this.em = em;
    }
}
