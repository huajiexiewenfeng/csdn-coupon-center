package com.csdn.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板规则
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/4 13:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

  /**
   * 折扣
   */
  private Discount discount;

  /**
   * 每个人领券最大数量
   */
  private Integer limitation;

  /**
   * 过期时间
   */
  private Long deadline;

}
