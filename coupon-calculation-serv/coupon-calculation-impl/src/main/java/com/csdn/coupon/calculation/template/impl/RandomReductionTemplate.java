package com.csdn.coupon.calculation.template.impl;

import com.csdn.coupon.calculation.template.AbstractRuleTemplate;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 随机立减
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/10 11:04
 */
@Component
@Slf4j
public class RandomReductionTemplate extends AbstractRuleTemplate {

  @Override
  protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount, Long quota) {
    int reductionAmount = new Random().nextInt(quota.intValue());
    return totalPrice - reductionAmount;
  }
}
