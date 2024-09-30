package choi.choice.service;

import choi.choice.domain.ProductSDO;
import choi.choice.result.ProductResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ApiProductServiceImpl implements ApiProductService {

    @Override
    @Transactional(value = "transactionalManager", propagation = Propagation.REQUIRES_NEW)
    public ProductResult insertProduct(ProductSDO productSDO, String authKey) {
        ProductResult productResult = new ProductResult();
        //TODO
        return null;
    }
}
