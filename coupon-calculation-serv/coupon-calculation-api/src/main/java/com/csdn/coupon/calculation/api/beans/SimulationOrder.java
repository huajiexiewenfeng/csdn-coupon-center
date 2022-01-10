package com.csdn.coupon.calculation.api.beans;

import com.csdn.coupon.template.api.beans.CouponInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 试算最优优惠券
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationOrder {

  private List<Product> products;

  private List<Long> couponIds;

  private List<CouponInfo> couponInfos;

  private Long userId;

}
