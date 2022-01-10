package com.csdn.coupon.customer.converter;

import com.csdn.coupon.customer.dao.entity.Coupon;
import com.csdn.coupon.template.api.beans.CouponInfo;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 14:09
 */
public class CouponConverter {

  public static CouponInfo convertToCouponInfo(Coupon coupon) {
    return CouponInfo.builder().id(coupon.getId()).shopId(coupon.getShopId())
        .status(coupon.getStatus().getCode()).template(coupon.getTemplateInfo())
        .templateId(coupon.getTemplateId()).userId(coupon.getUserId()).build();
  }

}
