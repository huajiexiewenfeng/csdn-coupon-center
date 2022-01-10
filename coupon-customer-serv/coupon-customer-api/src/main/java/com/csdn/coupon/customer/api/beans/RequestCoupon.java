package com.csdn.coupon.customer.api.beans;

import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 11:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {

  private Long userId;

  private Long couponTemplateId;

  private CouponTemplateInfo template;

  /**
   * Loadbalancer - 用作测试流量打标
   */
  private String trafficVersion;

}
