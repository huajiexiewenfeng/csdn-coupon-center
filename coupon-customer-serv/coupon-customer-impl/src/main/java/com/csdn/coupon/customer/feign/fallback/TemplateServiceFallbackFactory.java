package com.csdn.coupon.customer.feign.fallback;

import com.csdn.coupon.customer.feign.TemplateService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/11 14:19
 */
@Slf4j
@Component
public class TemplateServiceFallbackFactory implements FallbackFactory<TemplateService> {

  @Override
  public TemplateService create(Throwable cause) {
    return ids -> {
      log.warn("fallback getTemplateInBatch");
      return Maps.newHashMap();
    };
  }

}
