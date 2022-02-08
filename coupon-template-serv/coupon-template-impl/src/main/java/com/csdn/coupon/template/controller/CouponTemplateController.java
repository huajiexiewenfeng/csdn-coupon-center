package com.csdn.coupon.template.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.csdn.coupon.template.api.beans.TemplateSearchParams;
import com.csdn.coupon.template.service.CouponTemplateService;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 16:23
 */
@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

  @Autowired
  private CouponTemplateService couponTemplateService;

  @PostMapping("/addTemplate")
  public CouponTemplateInfo addTemplate(CouponTemplateInfo request) {
    log.info("Create coupon template,data={}", request);
    return couponTemplateService.createTemplate(request);
  }

  @PostMapping("/search")
  public PagedCouponTemplateInfo search(TemplateSearchParams request) {
    log.info("search templates,payload={}", request);
    return couponTemplateService.search(request);
  }

  @GetMapping("/getTemplate")
  @SentinelResource(value = "getTemplate")
  public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
    log.info("Load template, id={}", id);
    return couponTemplateService.getTemplateInfo(id);
  }

  /**
   * <p>你也可以通过defaultFallback属性做一个全局限流、降级的处理逻辑 <p/>
   * 如果你不想将降级方法写在当前类里，可以通过blockHandlerClass和fallbackClass指定"降级类"
   *
   * @param ids
   * @return
   */
  @GetMapping("/getBatch")
  @SentinelResource(value = "getTemplateInBatch", fallback = "getTemplateInBatchFallback", blockHandler = "getTemplateInBatchBlock")
  public Map<Long, CouponTemplateInfo> getTemplateInBatch(
      @RequestParam("ids") Collection<Long> ids) {
    log.info("getTemplateInBatch: {}", ids);
    return couponTemplateService.getTemplateInfoMap(ids);
  }

  public Map<Long, CouponTemplateInfo> getTemplateInBatchFallback(Collection<Long> ids) {
    log.info("接口降级");
    return Maps.newHashMap();
  }

  public Map<Long, CouponTemplateInfo> getTemplateInBatchBlock(Collection<Long> ids) {
    log.info("接口限流");
    return Maps.newHashMap();
  }

}
