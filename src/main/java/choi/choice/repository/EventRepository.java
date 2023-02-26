package choi.choice.repository;

import choi.choice.domain.Evt;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class EventRepository implements JpaRepository<Evt, Long> {
    @Override
    public List<Evt> findAll() {
        return null;
    }

    @Override
    public List<Evt> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Evt> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Evt> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Evt entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Evt> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Evt> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Evt> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Evt> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Evt> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Evt> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Evt getOne(Long aLong) {
        return null;
    }

    @Override
    public Evt getById(Long aLong) {
        return null;
    }

    @Override
    public Evt getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Evt> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Evt> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Evt> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Evt> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Evt> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Evt> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Evt, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Evt> S saveAndFlush(S entity) {
        return null;
    }
}
