package com.zys.product.service.impl;

import com.netflix.discovery.converters.Auto;
import com.rabbitmq.tools.json.JSONUtil;
import com.zys.product.dto.CartDTO;
import com.zys.product.enums.ProductStatus;
import com.zys.product.enums.ResultEnums;
import com.zys.product.exception.ProductException;
import com.zys.product.model.ProductInfo;
import com.zys.product.repository.ProductInfoRepository;
import com.zys.product.service.ProductInfoService;
import com.zys.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbin
 * @since 2019-12-30
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private @Autowired
    ProductInfoRepository productInfoRepository;
    private @Autowired
    AmqpTemplate amqpTemplate;
    @Override
    public List<ProductInfo> selectByStatus(Integer productStatus) {
        return productInfoRepository.findByProductStatus(productStatus);
    }

    @Override
    public List<ProductInfo> selectById(List<String> productId) {
        return productInfoRepository.findByProductIdIn(productId);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOS) {
        //先执行完所有的订单商品的库存的减才发送消息
        List<ProductInfo> productInfos = decreaseStockProcess(cartDTOS);
        //发送消息到order端
        amqpTemplate.convertAndSend("productInfo",JsonUtil.toJson(productInfos));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOS) {
        List<ProductInfo> productInfos = new ArrayList<>();
        for (CartDTO cartDTO : cartDTOS) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                throw new ProductException(ResultEnums.PRODUCT_IS_EXISTS);
            }
            ProductInfo product = productInfo.get();
            Integer stock = product.getProductStock()-cartDTO.getProductQuantity();
            if (stock < 0){
                throw new ProductException(ResultEnums.PRODUCT_IS_ENOUGH);
            }
            product.setProductStock(stock);
            productInfoRepository.save(product);
            productInfos.add(product);

        }
        return productInfos;
    }
}
