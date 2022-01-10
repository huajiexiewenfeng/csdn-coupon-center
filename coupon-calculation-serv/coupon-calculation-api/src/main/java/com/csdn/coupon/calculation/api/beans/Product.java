package com.csdn.coupon.calculation.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  /**
   * 商品id
   */
  private Long productId;

  /**
   * 价格
   */
  private long price;

  /**
   * 数量
   */
  private Integer count;

  /**
   * 商店
   */
  private Long shopId;

}
