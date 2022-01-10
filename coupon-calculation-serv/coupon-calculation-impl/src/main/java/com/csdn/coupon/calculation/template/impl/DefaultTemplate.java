package com.csdn.coupon.calculation.template.impl;

import com.csdn.coupon.calculation.template.AbstractRuleTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 14:00
 */
@Component
public class DefaultTemplate extends AbstractRuleTemplate {

  @Override
  protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount, Long quota) {
    return totalPrice;
  }
}
