package com.csdn.coupon.template.service;

import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.csdn.coupon.template.api.beans.TemplateSearchParams;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 16:24
 */
public interface CouponTemplateService {

  CouponTemplateInfo createTemplate(CouponTemplateInfo request);

  PagedCouponTemplateInfo search(TemplateSearchParams request);

  Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);

  CouponTemplateInfo getTemplateInfo(Long id);
}
