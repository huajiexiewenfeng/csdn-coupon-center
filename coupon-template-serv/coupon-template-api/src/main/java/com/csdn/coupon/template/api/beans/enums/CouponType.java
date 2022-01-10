package com.csdn.coupon.template.api.beans.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 11:24
 */
@Getter
@AllArgsConstructor
public enum CouponType {
  UNKNOW("unknow", "0"),
  MONEY_OFF("满减劵", "1"),
  DISCOUNT("打折", "2"),
  RANDOM_DISCOUNT("随机减", "3"),
  LONELY_NIGHT_MONEY_OFF("寂寞午夜double劵", "4"),
  ANTI_PUA("PUA加倍奉还劵", "5");

  private String description;

  private String code;

  public static CouponType convert(String code) {
   return Stream.of(values()).filter(couponType -> couponType.code.equalsIgnoreCase(code)).findFirst()
        .orElse(UNKNOW);
  }

}
