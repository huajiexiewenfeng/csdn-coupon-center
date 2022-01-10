package com.csdn.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/4 13:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfo {

  private Long id;

  private Long templateId;

  private Long userId;

  private Long shopId;

  private Integer status;

  private CouponTemplateInfo template;
}
