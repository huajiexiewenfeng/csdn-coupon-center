package com.csdn.coupon.customer.feign;

import com.csdn.coupon.customer.feign.fallback.TemplateServiceFallback;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import java.util.Collection;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/11 14:14
 */
@FeignClient(value = "coupon-template-serv", path = "/template", fallback = TemplateServiceFallback.class)
public interface TemplateService {

  @GetMapping("/getBatch")
  Map<Long, CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids);
}
