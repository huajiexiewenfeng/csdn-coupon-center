package com.csdn.coupon.customer.service;

import com.csdn.coupon.customer.api.beans.RequestCoupon;
import com.csdn.coupon.customer.api.beans.SearchCoupon;
import com.csdn.coupon.customer.dao.entity.Coupon;
import com.csdn.coupon.template.api.beans.CouponInfo;
import java.util.List;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 14:07
 */
public interface CouponCustomerService {

  Coupon requestCoupon(RequestCoupon request);

  void deleteCoupon(Long userId, Long couponId);

  List<CouponInfo> findCoupon(SearchCoupon request);
}
