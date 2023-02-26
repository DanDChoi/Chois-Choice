package choi.choice.repository;

import choi.choice.domain.Good;
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
public class ProductsRepository implements JpaRepository<Good, Long> {
    @Override
    public void deleteInBatch(Iterable<Good> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    public List<Good> findAll() {
        return null;
    }

    @Override
    public List<Good> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Good> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Good> findAllById(Iterable<Long> longs) {
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
    public void delete(Good entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Good> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Good> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Good> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Good> findById(Long aLong) {
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
    public <S extends Good> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Good> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Good> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Good getOne(Long aLong) {
        return null;
    }

    @Override
    public Good getById(Long aLong) {
        return null;
    }

    @Override
    public Good getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Good> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Good> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Good> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Good> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Good> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Good> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Good, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
