package com.csdn.coupon.customer.api.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 11:30
 */
@Getter
@AllArgsConstructor
public enum CouponStatus {

  AVAILABLE("未使用",1),
  USED("已使用",2),
  INACTIVE("已经注销",3);

  private String desc;
  private Integer code;

  public static CouponStatus convert(Integer code) {
    if (null == code) {
      return null;
    }
    return Stream.of(values()).filter(bean -> bean.code.equals(code)).findAny().orElse(null);
  }

}
