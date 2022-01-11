package com.csdn.coupon.customer.loadbalance;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @Author: xiewenfeng
 * @Date: 2022/1/11 10:12
 */
public class CanaryRuleConfiguration {

  @Bean
  public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
      Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
    String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
    ObjectProvider<ServiceInstanceListSupplier> lazyProvider = loadBalancerClientFactory
        .getLazyProvider(name,
            ServiceInstanceListSupplier.class);
    return new CanaryRule(name, lazyProvider);
  }

}
