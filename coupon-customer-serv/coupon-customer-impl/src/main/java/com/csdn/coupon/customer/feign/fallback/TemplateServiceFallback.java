package com.csdn.coupon.customer.feign.fallback;

import com.csdn.coupon.customer.feign.TemplateService;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/11 14:17
 */
@Component
@Slf4j
public class TemplateServiceFallback implements TemplateService {

  @Override
  public Map<Long, CouponTemplateInfo> getTemplateInBatch(Collection<Long> ids) {
    log.warn("fallback getTemplateInBatch");
    return Maps.newHashMap();
  }
}
