package choi.choice.repository;

import choi.choice.domain.good;
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
public class ProductsRepository implements JpaRepository<good, Long> {
    @Override
    public void deleteInBatch(Iterable<good> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    public List<good> findAll() {
        return null;
    }

    @Override
    public List<good> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<good> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<good> findAllById(Iterable<Long> longs) {
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
    public void delete(good entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends good> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends good> S save(S entity) {
        return null;
    }

    @Override
    public <S extends good> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<good> findById(Long aLong) {
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
    public <S extends good> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends good> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<good> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public good getOne(Long aLong) {
        return null;
    }

    @Override
    public good getById(Long aLong) {
        return null;
    }

    @Override
    public good getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends good> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends good> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends good> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends good> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends good> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends good> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends good, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
