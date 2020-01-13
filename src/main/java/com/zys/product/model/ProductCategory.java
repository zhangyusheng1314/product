package com.zys.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangbin
 * @since 2019-12-30
 */
@Data
@Entity
public class ProductCategory{

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer categoryId;
    /**
     * 类目名字
     */
	private String categoryName;
    /**
     * 类目编号
     */
	private Integer categoryType;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date updateTime;

}
