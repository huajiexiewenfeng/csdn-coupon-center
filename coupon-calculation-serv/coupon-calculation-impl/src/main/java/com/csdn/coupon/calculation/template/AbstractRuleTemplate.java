package com.csdn.coupon.calculation.template;

import com.csdn.coupon.calculation.api.beans.Product;
import com.csdn.coupon.calculation.api.beans.ShoppingCart;
import com.csdn.coupon.template.api.beans.CouponInfo;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.rules.TemplateRule;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/5 10:39
 */
@Slf4j
public abstract class AbstractRuleTemplate implements RuleTemplate {

  @Override
  public ShoppingCart calculate(ShoppingCart cart) {
    // 获取订单总价格
    long totalPrice = getTotalPrice(cart.getProducts());
    // 获取商品总价根据门店分组
    Map<Long, Long> totalPriceByShop = getTotalPriceByShop(cart.getProducts());
    // 获取优惠券
    List<CouponInfo> coupons = cart.getCouponInfos();
    if (CollectionUtils.isEmpty(coupons)) {
      log.warn("There are no coupons");
      // 没有优惠券，返回原价
      cart.setCost(totalPrice);
      cart.setCouponInfos(Collections.emptyList());
      return cart;
    }

    // todo 假设只有一个优惠券
    CouponInfo couponInfo = cart.getCouponInfos().get(0);
    CouponTemplateInfo template = couponInfo.getTemplate();
    // 获取最低消费额
    TemplateRule rule = template.getRule();
    Long threshold = rule.getDiscount().getThreshold();
    // 优惠金额或者打折比例
    Long quota = rule.getDiscount().getQuota();
    // 门店
    Long shopId = template.getShopId();
    // 门店总价格
    Long shopTotalAmount =
        (ObjectUtils.isEmpty(shopId)) ? totalPrice : totalPriceByShop.get(shopId);
    if (ObjectUtils.isEmpty(shopTotalAmount) || shopTotalAmount < threshold) {
      log.warn("Totals of amount not meet,ur coupons are not application to this order");
      cart.setCost(totalPrice);
      cart.setCouponInfos(Collections.emptyList());
      return cart;
    }
    // 子类中计算新的价格
    Long newPrice = calculateNewPrice(totalPrice, shopTotalAmount, quota);

    if (newPrice < minCost()) {
      newPrice = minCost();
    }
    cart.setCost(newPrice);
    return cart;
  }

  /**
   * @param totalPrice
   * @param shopTotalAmount
   * @param quota
   * @return
   */
  abstract protected Long calculateNewPrice(long totalPrice, Long shopTotalAmount,
      Long quota);

  /**
   * 获取商品总价
   */
  protected long getTotalPrice(List<Product> products) {
    return products.stream().mapToLong(product -> {
      return product.getPrice() * product.getCount();
    }).sum();
  }

  /**
   * 获取商品总价根据门店分组
   */
  protected Map<Long, Long> getTotalPriceByShop(List<Product> products) {
    return products.stream().collect(Collectors.groupingBy(Product::getShopId,
        Collectors.summingLong(product -> product.getPrice() * product.getCount())));
  }

  /**
   * 每个订单至少支付 1 分钱
   */
  protected long minCost() {
    return 1L;
  }

  protected long convertToDecimal(Double value) {
    return new BigDecimal(value).setScale(0, RoundingMode.HALF_UP).longValue();
  }

}
