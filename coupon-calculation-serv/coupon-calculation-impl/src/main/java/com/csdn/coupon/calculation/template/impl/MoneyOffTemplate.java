package com.csdn.coupon.calculation.template.impl;

import com.csdn.coupon.calculation.template.AbstractRuleTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 13:50
 */
@Component
public class MoneyOffTemplate extends AbstractRuleTemplate {

  @Override
  protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount, Long quota) {
    return totalPrice - quota;
  }

}
