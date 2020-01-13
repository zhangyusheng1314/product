package com.zys.product.service;

import com.zys.product.model.ProductCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbin
 * @since 2019-12-30
 */
public interface ProductCategoryService{
    List<ProductCategory> selectByType(List<Integer> types);
}
