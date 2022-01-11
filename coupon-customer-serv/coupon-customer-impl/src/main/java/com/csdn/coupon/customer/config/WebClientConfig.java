package com.csdn.coupon.customer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/10 17:18
 */
@Configuration
public class WebClientConfig {

  @Bean
  @LoadBalanced
  public WebClient.Builder register() {
    return WebClient.builder();
  }

}
