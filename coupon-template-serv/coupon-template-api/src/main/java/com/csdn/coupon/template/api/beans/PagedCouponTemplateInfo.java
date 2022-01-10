package com.csdn.coupon.template.api.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 13:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedCouponTemplateInfo {

  public List<CouponTemplateInfo> templates;

  public int page;

  public long total;

}
