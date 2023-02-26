package choi.choice.service;

import choi.choice.domain.Good;
import choi.choice.repository.GoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodServiceImpl implements GoodService{

    private GoodRepository goodRepository;
    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Override
    public void add(Good good) {
        goodRepository.save(good);
    }

    @Override
    public Good findByNo(String id) {
        return null;
    }

    @Override
    public void deleteByNo(String id){
        goodRepository.deleteById(id);
    }
}
