package com.zys.product.service;

import com.zys.product.dto.CartDTO;
import com.zys.product.model.ProductInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbin
 * @since 2019-12-30
 */
public interface ProductInfoService{
    List<ProductInfo> selectByStatus(Integer productStatus);

    List<ProductInfo> selectById(List<String> productId);

    void decreaseStock(List<CartDTO> cartDTOS);
}
