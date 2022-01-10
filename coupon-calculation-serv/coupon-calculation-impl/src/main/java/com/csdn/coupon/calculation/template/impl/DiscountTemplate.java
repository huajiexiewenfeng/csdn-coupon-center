package com.csdn.coupon.calculation.template.impl;

import com.csdn.coupon.calculation.template.AbstractRuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 14:02
 */
@Component
@Slf4j
public class DiscountTemplate extends AbstractRuleTemplate {

  @Override
  protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount, Long quota) {
    long newPrice = super.convertToDecimal(shopTotalAmount * (quota.doubleValue() / 100));
    // 获取商定优惠以外的商品价格
    long otherProductPrice = totalPrice - shopTotalAmount;
    newPrice += otherProductPrice;
    log.info("original price = {},new price = {}", totalPrice, newPrice);
    return newPrice;
  }
}
