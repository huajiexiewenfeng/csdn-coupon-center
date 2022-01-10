package com.csdn.coupon.template.converter;

import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.dao.entity.CouponTemplate;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 16:31
 */
public class CouponTemplateConverter {

  public static CouponTemplateInfo convertToTemplateInfo(CouponTemplate template) {
    return CouponTemplateInfo.builder().
        id(template.getId()).
        name(template.getName()).
        available(template.getAvailable()).
        desc(template.getDescription()).
        rule(template.getRule()).
        shopId(template.getShopId()).
        type(template.getCategory().getCode()).
        build();
  }
}
