package com.csdn.coupon.template.dao.entity;

import com.csdn.coupon.template.api.beans.enums.CouponType;
import com.csdn.coupon.template.api.beans.rules.TemplateRule;
import com.csdn.coupon.template.dao.converter.RuleConverter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/4 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "available", nullable = false)
  private Boolean available;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "shop_id", nullable = false)
  private Long shopId;

  @Column(name = "type", nullable = false)
  private CouponType category;

  @CreatedDate
  @Column(name = "created_time", nullable = false)
  private Date createdTime;

  @Column(name = "rule", nullable = false)
  @Convert(converter = RuleConverter.class)
  private TemplateRule rule;

}
