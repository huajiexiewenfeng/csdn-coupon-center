package com.csdn.coupon.calculation.service.impl;

import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.calculation.api.beans.SimulationOrder;
import com.csdn.coupon.calculation.api.beans.SimulationResponse;
import com.csdn.coupon.calculation.service.CouponCalculationService;
import com.csdn.coupon.calculation.template.CouponTemplateFactory;
import com.csdn.coupon.calculation.template.RuleTemplate;
import com.csdn.coupon.template.api.beans.CouponInfo;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:27
 */
@Slf4j
@Service
public class CouponCalculationImpl implements CouponCalculationService {

  @Autowired
  private CouponTemplateFactory couponTemplateFactory;

  @Override
  public ShoppingCart calculationOrderPrice(ShoppingCart cart) {
    log.info("calculate order price: {}", cart);
    RuleTemplate template = couponTemplateFactory.getTemplate(cart);
    return template.calculate(cart);
  }

  @Override
  public SimulationResponse simulateOrder(SimulationOrder order) {
    SimulationResponse response = new SimulationResponse();
    // 防止出现 0 的价格
    Long minOrderPrice = Long.MAX_VALUE;

    for (CouponInfo couponInfo : order.getCouponInfos()) {
      // 计算优惠券订单价格
      ShoppingCart cart = new ShoppingCart();
      cart.setCouponInfos(Collections.singletonList(couponInfo));
      cart.setProducts(order.getProducts());
      cart = couponTemplateFactory.getTemplate(cart).calculate(cart);

      Long couponId = couponInfo.getId();
      Long orderPrice = cart.getCost();

      response.getCouponToOrderPrice().put(couponId, orderPrice);
      // 比较订单价格，设置最优
      if (minOrderPrice > orderPrice) {
        response.setBestCouponId(couponId);
        minOrderPrice = orderPrice;
      }
    }

    return response;
  }
}
