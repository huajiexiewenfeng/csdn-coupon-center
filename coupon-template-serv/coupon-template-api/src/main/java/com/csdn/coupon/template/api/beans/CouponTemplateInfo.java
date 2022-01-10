package com.csdn.coupon.template.api.beans;

import com.csdn.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券模板
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/4 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {

  private Long id;

  private String name;

  private String desc;

  /**
   * 优惠券类型
   */
  private String type;

  /**
   * 适用门店 - 若无则为全店通用券
   */
  private Long shopId;

  /**
   * 优惠券规则
   */
  private TemplateRule rule;

  /**
   * 当前模板的使用状态
   */
  private Boolean available;

}
