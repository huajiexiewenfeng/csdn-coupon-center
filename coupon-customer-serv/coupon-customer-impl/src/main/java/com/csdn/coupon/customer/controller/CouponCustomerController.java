package com.csdn.coupon.customer.controller;

import com.csdn.coupon.customer.api.beans.RequestCoupon;
import com.csdn.coupon.customer.api.beans.SearchCoupon;
import com.csdn.coupon.customer.dao.entity.Coupon;
import com.csdn.coupon.customer.service.CouponCustomerService;
import com.csdn.coupon.template.api.beans.CouponInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 14:12
 */
@Slf4j
@RestController
@RequestMapping("/coupon-customer")
@RefreshScope
public class CouponCustomerController implements EnvironmentAware {

  private Environment environment;

  @Value("${disableCouponRequest:false}")
  private Boolean disableCoupon;

  @Autowired
  private CouponCustomerService couponCustomerService;

  /**
   * 获取优惠券
   *
   * @return
   */
  @PostMapping("/requestCoupon")
  public Coupon requestCoupon(RequestCoupon request) {
    /**
     * 在没有 @RefreshScope 的情况下，通过 environment 也能实时获取最新的 nacos 环境变量
     */
    Boolean disableCouponRequest = environment.getProperty("disableCouponRequest", Boolean.class);
    System.out.println("通过环境变量获取：" + disableCouponRequest);
    if (this.disableCoupon) {
      log.info("暂停领取优惠券");
      return null;
    }
    return couponCustomerService.requestCoupon(request);
  }

  /**
   * 获取优惠券
   *
   * @return
   */
  @DeleteMapping("/deleteCoupon")
  public void deleteCoupon(@RequestParam("userId") Long userId,
      @RequestParam("couponId") Long couponId) {
    couponCustomerService.deleteCoupon(userId, couponId);
  }

  /**
   * 查询优惠券
   *
   * @return
   */
  @PostMapping("/findCoupon")
  public List<CouponInfo> findCoupon(SearchCoupon request) {
    return couponCustomerService.findCoupon(request);
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
