package choi.choice.repository;

import choi.choice.domain.mbr;
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
public class MbrRepository implements JpaRepository<mbr, Long> {

    @Override
    public List<mbr> findAll() {
        return null;
    }

    @Override
    public List<mbr> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<mbr> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<mbr> findAllById(Iterable<Long> longs) {
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
    public void delete(mbr entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends mbr> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends mbr> S save(S entity) {
        return null;
    }

    @Override
    public <S extends mbr> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<mbr> findById(String aLong) {
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
    public <S extends mbr> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends mbr> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<mbr> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public mbr getOne(Long aLong) {
        return null;
    }

    @Override
    public mbr getById(Long aLong) {
        return null;
    }

    @Override
    public mbr getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends mbr> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends mbr> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends mbr> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends mbr> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends mbr> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends mbr> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends mbr, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
