package com.csdn.coupon.calculation.template;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:34
 */
public interface RuleTemplate {

  /**
   * 计算
   *
   * @param cart
   * @return
   */
  ShoppingCart calculate(ShoppingCart cart);

}
