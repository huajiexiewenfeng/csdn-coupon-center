package com.csdn.coupon.customer.dao.convertor;

import com.csdn.coupon.customer.api.enums.CouponStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 13:53
 */
@Converter
public class CouponStatusConverter implements AttributeConverter<CouponStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(CouponStatus couponStatus) {
    return couponStatus.getCode();
  }

  @Override
  public CouponStatus convertToEntityAttribute(Integer code) {
    return CouponStatus.convert(code);
  }
}
