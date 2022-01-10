package com.csdn.coupon.calculation.template;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.calculation.template.impl.DefaultTemplate;
import com.csdn.coupon.calculation.template.impl.DiscountTemplate;
import com.csdn.coupon.calculation.template.impl.MoneyOffTemplate;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:35
 */
@Component
@Slf4j
public class CouponTemplateFactory {

  @Autowired
  private MoneyOffTemplate moneyOffTemplate;

  @Autowired
  private DefaultTemplate defaultTemplate;

  @Autowired
  private DiscountTemplate discountTemplate;

  public RuleTemplate getTemplate(ShoppingCart order) {
    if (CollectionUtils.isEmpty(order.getCouponInfos())) {
      return defaultTemplate;
    }
    CouponTemplateInfo template = order.getCouponInfos().get(0).getTemplate();
    CouponType type = CouponType.convert(template.getType());
    switch (type) {
      case ANTI_PUA:
        return defaultTemplate;
      case DISCOUNT:
        return discountTemplate;
      case MONEY_OFF:
        return moneyOffTemplate;
      case RANDOM_DISCOUNT:
        return defaultTemplate;
      case LONELY_NIGHT_MONEY_OFF:
        return defaultTemplate;
      default:
        return defaultTemplate;
    }
  }
}
