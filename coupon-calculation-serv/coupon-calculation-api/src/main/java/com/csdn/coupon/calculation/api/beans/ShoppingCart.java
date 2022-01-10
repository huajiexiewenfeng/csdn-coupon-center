package com.csdn.coupon.calculation.api.beans;

import com.csdn.coupon.template.api.beans.CouponInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

  private List<Product> products;

  private Long couponId;

  private long cost;

  private List<CouponInfo> couponInfos;

  private Long userId;

}
