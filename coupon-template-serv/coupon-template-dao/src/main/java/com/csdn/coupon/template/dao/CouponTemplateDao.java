package com.csdn.coupon.template.dao;

import com.csdn.coupon.template.dao.entity.CouponTemplate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 14:55
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {

  /**
   * 根据 shopId 查询所有优惠券模板
   *
   * @param shopId
   * @return
   */
  List<CouponTemplate> findAllByShopId(Long shopId);


  /**
   * IN & 分页
   *
   * @param id
   * @param page
   * @return
   */
  Page<CouponTemplate> findAllByIdIn(List<Long> id, Pageable page);

  /**
   * count 统计
   *
   * @param shopId
   * @param available
   * @return
   */
  Integer countByShopIdAndAvailable(Long shopId, Boolean available);

  /**
   * 设置优惠券不可用
   *
   * @param id
   * @return
   */
  @Modifying
  @Query("update CouponTemplate c set c.available = 0 where c.id = :id")
  int makeCouponUnavailable(@Param("id") Long id);


}
