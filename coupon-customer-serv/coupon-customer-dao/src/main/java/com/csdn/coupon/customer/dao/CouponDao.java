package com.csdn.coupon.customer.dao;

import com.csdn.coupon.customer.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 13:50
 */
public interface CouponDao extends JpaRepository<Coupon, Long> {

  long countByUserIdAndTemplateId(Long userId, Long templateId);
}
