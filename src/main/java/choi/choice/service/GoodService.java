package choi.choice.service;

import choi.choice.domain.good;

import java.util.List;

public interface GoodService {

    public List<good> findAll();
    public void add(good good);

    good findByNo(String id);

}
