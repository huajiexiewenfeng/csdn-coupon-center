package com.csdn.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 13:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateSearchParams {

  private Long id;

  private String name;

  private String type;

  private Long shopId;

  private Boolean available;

  private int page;

  private int pageSize;
}
