package com.zys.product.service.impl;

import com.zys.product.model.ProductCategory;
import com.zys.product.repository.ProductCategoryRepository;
import com.zys.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbin
 * @since 2019-12-30
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> selectByType(List<Integer> types) {
        return productCategoryRepository.findByCategoryTypeIn(types);
    }
}
