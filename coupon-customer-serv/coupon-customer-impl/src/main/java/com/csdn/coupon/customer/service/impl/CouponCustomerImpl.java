package com.csdn.coupon.customer.service.impl;

import static java.util.stream.Collectors.toList;

import com.csdn.coupon.customer.api.beans.RequestCoupon;
import com.csdn.coupon.customer.api.beans.SearchCoupon;
import com.csdn.coupon.customer.api.enums.CouponStatus;
import com.csdn.coupon.customer.converter.CouponConverter;
import com.csdn.coupon.customer.dao.CouponDao;
import com.csdn.coupon.customer.dao.entity.Coupon;
import com.csdn.coupon.customer.feign.TemplateService;
import com.csdn.coupon.customer.service.CouponCustomerService;
import com.csdn.coupon.template.api.beans.CouponInfo;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
//import com.csdn.coupon.template.service.CouponTemplateService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 14:08
 */
@Service
@Slf4j
public class CouponCustomerImpl implements CouponCustomerService {

  @Autowired
  private CouponDao couponDao;

  @Autowired
  private TemplateService templateService;

  @Autowired
  private WebClient.Builder webClientBuilder;

  @Override
  public Coupon requestCoupon(RequestCoupon request) {
    log.info("暂无实现");
    return null;
  }

  @Override
  public void deleteCoupon(Long userId, Long couponId) {
    // 封装查询条件
    Coupon example = Coupon.builder().userId(userId)
        .id(couponId)
        .build();
    // 找到对应记录
    Coupon coupon = couponDao.findAll(Example.of(example)).stream().findFirst()
        .orElseThrow(() -> new RuntimeException("Could not find available coupon"));
    // 设置状态
    coupon.setStatus(CouponStatus.INACTIVE);
    // 保存
    couponDao.save(coupon);
  }

  @Override
  public List<CouponInfo> findCoupon(SearchCoupon request) {
    Coupon example = Coupon.builder().userId(request.getUserId())
        .status(CouponStatus.convert(request.getCouponStatus())).shopId(request.getShopId())
        .build();

    List<Coupon> coupons = couponDao.findAll(Example.of(example));
    if (CollectionUtils.isEmpty(coupons)) {
      return Collections.emptyList();
    }

    List<Long> templateIds = coupons.stream().map(Coupon::getTemplateId).collect(toList());

//    Map<Long, CouponTemplateInfo> templateInfoMap = templateService.getTemplateInfoMap(templateIds);
//    Map<Long, CouponTemplateInfo> templateInfoMap = webClientBuilder.build().get()
//        .uri("http://coupon-template-serv/template/getBatch?ids=" + templateIds).retrieve()
//        .bodyToMono(new ParameterizedTypeReference<Map<Long, CouponTemplateInfo>>() {
//        }).block();
    Map<Long, CouponTemplateInfo> templateInfoMap = templateService.getTemplateInBatch(templateIds);

    coupons.forEach(coupon -> {
      coupon.setTemplateInfo(templateInfoMap.get(coupon.getTemplateId()));
    });

    return coupons.stream().map(CouponConverter::convertToCouponInfo).collect(toList());
  }
}
