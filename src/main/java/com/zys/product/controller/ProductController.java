package com.zys.product.controller;

import com.zys.product.dto.CartDTO;
import com.zys.product.enums.ProductStatus;
import com.zys.product.model.ProductCategory;
import com.zys.product.model.ProductInfo;
import com.zys.product.service.ProductCategoryService;
import com.zys.product.service.ProductInfoService;
import com.zys.product.utils.ResultVOUtils;
import com.zys.product.vo.ProductInfoVO;
import com.zys.product.vo.ProductVO;
import com.zys.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired private ProductInfoService productInfoService;
    @Autowired private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ResultVO<ProductVO> list(){
        //查询上架的商品
        List<ProductInfo> productInfos = productInfoService.selectByStatus(ProductStatus.UP.getStatus());
        //查询上架的商品的类别列表
        List<Integer> categoryTypes = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //查询产品类别
        List<ProductCategory> productCategories = productCategoryService.selectByType(categoryTypes);
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOS);
            productVOS.add(productVO);
        }
        return ResultVOUtils.success(productVOS);
    }
    @RequestMapping("/getListById")
    public List<ProductInfo> getListById(@RequestBody List<String> productId) throws InterruptedException {
        Thread.sleep(2000);
        List<ProductInfo> productInfos = productInfoService.selectById(productId);
        return productInfos;
    }
    @RequestMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOS){
        productInfoService.decreaseStock(cartDTOS);
    }
}
