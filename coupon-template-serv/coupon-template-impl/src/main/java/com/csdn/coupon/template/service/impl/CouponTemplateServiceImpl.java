package com.csdn.coupon.template.service.impl;

import com.csdn.coupon.template.api.beans.CouponTemplateInfo;
import com.csdn.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.csdn.coupon.template.api.beans.TemplateSearchParams;
import com.csdn.coupon.template.api.beans.enums.CouponType;
import com.csdn.coupon.template.converter.CouponTemplateConverter;
import com.csdn.coupon.template.dao.CouponTemplateDao;
import com.csdn.coupon.template.dao.entity.CouponTemplate;
import com.csdn.coupon.template.service.CouponTemplateService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 16:24
 */
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

  @Value("${shop.limitation:100}")
  private Integer shopLimitation;

  @Autowired
  private CouponTemplateDao templateDao;

  @Override
  public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
    // 每个商店不能超过 100 张优惠券
    if (!ObjectUtils.isEmpty(request.getShopId())) {
      Integer count = templateDao.countByShopIdAndAvailable(request.getShopId(), true);
      if (count > shopLimitation) {
        log.error("the totals of coupon template exceeds maximum number:{}", shopLimitation);
        throw new UnsupportedOperationException(
            "exceeded the maximum of coupon templates that you can crate");
      }
    }
    // 创建优惠券
    CouponTemplate template = CouponTemplate.builder().
        name(request.getName()).
        description(request.getDesc()).
        category(CouponType.convert(request.getType())).
        available(true).
        shopId(request.getShopId()).
        rule(request.getRule()).build();
    // 保存
    template = templateDao.save(template);
    return CouponTemplateConverter.convertToTemplateInfo(template);
  }

  @Override
  public PagedCouponTemplateInfo search(TemplateSearchParams request) {
    // TemplateSearchParams -> CouponTemplate
    CouponTemplate example = CouponTemplate.builder().shopId(request.getShopId())
        .category(CouponType.convert(request.getType())).available(request.getAvailable())
        .name(request.getName()).build();

    PageRequest page = PageRequest.of(request.getPage(), request.getPageSize());
    Page<CouponTemplate> result = templateDao.findAll(Example.of(example), page);

    List<CouponTemplateInfo> couponTemplates = result.stream()
        .map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toList());

    return PagedCouponTemplateInfo.builder().page(request.getPage())
        .total(result.getTotalElements())
        .templates(couponTemplates).build();
  }

  @Override
  public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
    List<CouponTemplate> templates = templateDao.findAllById(ids);

    return templates.stream().map(CouponTemplateConverter::convertToTemplateInfo)
        .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
  }

}
