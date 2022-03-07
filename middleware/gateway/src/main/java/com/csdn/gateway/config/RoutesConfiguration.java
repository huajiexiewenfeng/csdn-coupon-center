package com.csdn.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 路由配置
 *
 * @Author: xiewenfeng
 * @Date: 2022/3/7 14:06
 */
@Configuration
public class RoutesConfiguration {

  @Autowired
  private KeyResolver hostAddrKeyResolver;

  @Autowired
  @Qualifier("templateRateLimiter")
  private RedisRateLimiter templateRateLimiter;

  @Autowired
  @Qualifier("customerRateLimiter")
  private RedisRateLimiter customerRateLimiter;

  @Bean
  public RouteLocator declare(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(route -> route
            .path("/gateway/coupon-customer/**")
            .filters(f -> f.stripPrefix(1).requestRateLimiter(
                limiter -> {
                  limiter.setKeyResolver(hostAddrKeyResolver);
                  limiter.setRateLimiter(customerRateLimiter);
                  // 限流失败后返回的 http status
                  limiter.setStatusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
                }
            ))
            .uri("lb://coupon-customer-serv"))
        .route(route -> route
            .order(1) // 设置优先级
            .path("/gateway/coupon-template/**")
            .filters(f -> f.stripPrefix(1)
                .removeRequestHeader("myLove")
                .addRequestHeader("myLove", "xwf")
                .removeResponseHeader("responseHeader"))
            .uri("lb://coupon-template-serv"))
        .route(route -> route
            .path("/gateway/coupon-calculator/**")
            .filters(f -> f.stripPrefix(1))
            .uri("lb://coupon-calculator-serv")).build();
  }

}
