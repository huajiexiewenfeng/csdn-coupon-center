package com.csdn.coupon.template.controller;

import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.csdn.coupon.template.api.beans.TemplateSearchParams;
import com.csdn.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
