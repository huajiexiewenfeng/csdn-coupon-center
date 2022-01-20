package com.csdn.coupon.customer.config;

import feign.Logger;
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

  /**
   * NONE：不记录任何信息，这是 OpenFeign 默认的日志级别；
   * BASIC：只记录服务请求的 URL、HTTP Method、响应状态码（如 200、404 等）和服务调用的执行时间；
   * HEADERS：在 BASIC 的基础上，还记录了请求和响应中的 HTTP Headers；
   * FULL：在 HEADERS 级别的基础上，还记录了服务请求和服务响应中的 Body 和 metadata，FULL 级别记录了最完成的调用信息。
   * @return
   */
  @Bean
  Logger.Level feignLogger() {
    return Logger.Level.FULL;
  }
}
