package com.csdn.coupon.template.dao.converter;

import com.csdn.coupon.template.api.beans.enums.CouponType;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 14:50
 */
@Convert
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {

  @Override
  public String convertToDatabaseColumn(CouponType couponType) {
    return couponType.getCode();
  }

  @Override
  public CouponType convertToEntityAttribute(String code) {
    return CouponType.convert(code);
  }
}
