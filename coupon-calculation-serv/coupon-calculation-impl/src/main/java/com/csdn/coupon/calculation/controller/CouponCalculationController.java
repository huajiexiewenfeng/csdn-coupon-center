package com.csdn.coupon.calculation.controller;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.calculation.api.beans.SimulationOrder;
import com.csdn.coupon.calculation.api.beans.SimulationResponse;
import com.csdn.coupon.calculation.service.CouponCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:28
 */
@Slf4j
@RestController
@RequestMapping("/calculator")
public class CouponCalculationController {

  @Autowired
  private CouponCalculationService couponCalculationService;

  /**
   * 购物车结算
   *
   * @param settlement
   * @return
   */
  @PostMapping("/checkout")
  public ShoppingCart calculationOrderPrice(ShoppingCart settlement) {
    log.info("do calculation: {}", settlement);
    return couponCalculationService.calculationOrderPrice(settlement);
  }

  /**
   * 优惠券列表试算
   *
   * @param simulator
   * @return
   */
  @PostMapping("/simulate")
  public SimulationResponse simulate(SimulationOrder simulator) {
    log.info("do simulation: {}", simulator);
    return couponCalculationService.simulateOrder(simulator);
  }

}
