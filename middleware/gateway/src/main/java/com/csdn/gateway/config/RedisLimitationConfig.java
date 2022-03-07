package com.csdn.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * redis 限流器
 *
 * @Author: xiewenfeng
 * @Date: 2022/3/7 14:15
 */
@Configuration
public class RedisLimitationConfig {

  @Bean
  @Primary
  public KeyResolver remoteHostLimitationKey() {
    return exchange ->
        Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
  }

  @Bean("templateRateLimiter")
  public RedisRateLimiter templateRateLimiter() {
    return new RedisRateLimiter(10, 20);
  }

  @Bean("customerRateLimiter")
  public RedisRateLimiter customerRateLimiter() {
    return new RedisRateLimiter(20, 40);
  }

  @Bean("defaultRateLimiter")
  @Primary
  public RedisRateLimiter defaultRateLimiter() {
    return new RedisRateLimiter(50, 100);
  }

}
