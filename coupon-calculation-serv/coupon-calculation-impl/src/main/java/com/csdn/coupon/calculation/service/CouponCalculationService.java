package com.csdn.coupon.calculation.service;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.calculation.api.beans.SimulationOrder;
import com.csdn.coupon.calculation.api.beans.SimulationResponse;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:27
 */
public interface CouponCalculationService {

  ShoppingCart calculationOrderPrice(ShoppingCart settlement);

  SimulationResponse simulateOrder(SimulationOrder simulator);
}
