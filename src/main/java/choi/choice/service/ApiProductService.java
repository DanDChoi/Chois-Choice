package choi.choice.service;

import choi.choice.domain.ProductSDO;
import choi.choice.result.ProductResult;

public interface ApiProductService {

    ProductResult insertProduct(ProductSDO productSDO, String authKey);

}
