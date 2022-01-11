package com.csdn.coupon.customer.feign;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.calculation.api.beans.SimulationOrder;
import com.csdn.coupon.calculation.api.beans.SimulationResponse;
import com.csdn.coupon.customer.feign.fallback.TemplateServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/11 14:22
 */
@FeignClient(value = "coupon-calculator-serv", path = "/calculator")
public interface CalculationService {

  /**
   * 购物车结算
   *
   * @param settlement
   * @return
   */
  @PostMapping("/checkout")
  ShoppingCart calculationOrderPrice(ShoppingCart settlement);

  /**
   * 优惠券列表试算
   *
   * @param simulator
   * @return
   */
  @PostMapping("/simulate")
  SimulationResponse simulate(SimulationOrder simulator);
}
