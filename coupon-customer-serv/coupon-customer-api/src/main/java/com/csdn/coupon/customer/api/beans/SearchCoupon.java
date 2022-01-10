package com.csdn.coupon.customer.api.beans;

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
public class SearchCoupon {

  private Long userId;

  private Long shopId;

  private Integer couponStatus;

}
