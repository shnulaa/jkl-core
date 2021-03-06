package com.jkinvest.jkl.core.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jkinvest.jkl.core.demo.entity.Product;

/**
 * <p>
 * 业务接口
 * 商品
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
public interface ProductService extends IService<Product> {

    boolean saveEx(Product data);
}
