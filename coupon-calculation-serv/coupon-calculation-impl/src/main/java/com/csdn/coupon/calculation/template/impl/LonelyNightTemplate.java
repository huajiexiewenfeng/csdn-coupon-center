package com.csdn.coupon.calculation.template.impl;

import com.csdn.coupon.calculation.template.AbstractRuleTemplate;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 午夜10点到次日凌晨2点之间下单，优惠金额翻倍
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/10 10:58
 */
@Component
@Slf4j
public class LonelyNightTemplate extends AbstractRuleTemplate {

  @Override
  protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount, Long quota) {
    Calendar calender = Calendar.getInstance();
    int hourOfDay = calender.get(Calendar.HOUR_OF_DAY);
    if (hourOfDay >= 23 || hourOfDay < 2) {
      quota *= 2;
    }
    return totalPrice - quota;
  }
}
