package choi.choice.service;

import choi.choice.domain.good;
import choi.choice.repository.GoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodServiceImpl implements GoodService{

    private GoodRepository goodRepository;
    @Override
    public List<good> findAll() {
        return null;
    }

    @Override
    public void add(good good) {
        goodRepository.save(good);
    }

    @Override
    public good findByNo(String id) {
        return null;
    }

    @Override
    public void deleteByNo(String id){
        goodRepository.deleteById(id);
    }
}
