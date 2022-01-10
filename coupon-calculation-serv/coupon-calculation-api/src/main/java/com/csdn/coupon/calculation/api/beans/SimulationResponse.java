package com.csdn.coupon.calculation.api.beans;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResponse {

  /**
   * 最佳优惠券
   */
  private Long bestCouponId;

  /**
   * 没一个优惠券对应订单的价格
   */
  private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();

}
