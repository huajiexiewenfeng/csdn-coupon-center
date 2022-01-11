package com.csdn.coupon.customer.loadbalance;

import com.csdn.coupon.customer.constant.Constant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultRequestContext;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestData;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * 金丝雀规则
 *
 * @Author: xiewenfeng
 * @Date: 2022/1/10 17:48
 */
@Slf4j
public class CanaryRule implements ReactorServiceInstanceLoadBalancer {

  private String serviceId;

  private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

  final AtomicInteger position;

  public CanaryRule(String serviceId,
      ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
    this.serviceId = serviceId;
    this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    position = new AtomicInteger(new Random().nextInt(1000));
  }

  @Override
  public Mono<Response<ServiceInstance>> choose(Request request) {
    /**
     * https://blog.csdn.net/xiewenfeng520/article/details/105652843</p>
     * IoC 依赖查找（专题）</p>
     * 7.3 ObjectProvider#getIfAvailable（安全）</p>
     */
    ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable(
        NoopServiceInstanceListSupplier::new);
    return supplier.get(request).next()
        .map(serviceInstances -> processInstanceResponse(supplier, serviceInstances, request));
  }

  private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
      List<ServiceInstance> serviceInstances, Request request) {
    Response<ServiceInstance> instanceResponse = getInstanceResponse(serviceInstances, request);

    if (supplier instanceof SelectedInstanceCallback && instanceResponse.hasServer()) {
      ((SelectedInstanceCallback) supplier).selectedServiceInstance(instanceResponse.getServer());
    }

    return instanceResponse;
  }

  Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
    if (CollectionUtils.isEmpty(instances)) {
      log.warn("There are not instance available {}", serviceId);
      return new EmptyResponse();
    }
    // 从 header 中获取特定的流量打标值
    DefaultRequestContext context = (DefaultRequestContext) request.getContext();
    RequestData requestData = (RequestData) context.getClientRequest();
    HttpHeaders headers = requestData.getHeaders();
    String trafficVersion = headers.getFirst(Constant.TRAFFIC_VERSION);
    // 如果没有标记
    if (ObjectUtils.isEmpty(trafficVersion)) {
      // 过滤掉所有金丝雀的服务节点
      List<ServiceInstance> noneCanaryServiceInstances = instances.stream()
          .filter(serviceInstance -> {
            return !serviceInstance.getMetadata().containsKey(Constant.TRAFFIC_VERSION);
          }).collect(Collectors.toList());
      return getRoundRobinInstance(noneCanaryServiceInstances);
    }
    // 找到所有金丝雀的服务节点
    List<ServiceInstance> canaryInstances = instances.stream()
        .filter(serviceInstance -> {
          return StringUtils.endsWithIgnoreCase(
              serviceInstance.getMetadata().get(Constant.TRAFFIC_VERSION), trafficVersion);
        }).collect(Collectors.toList());
    return getRoundRobinInstance(canaryInstances);
  }

  /**
   * 轮询获取服务节点
   *
   * @param serviceInstances
   * @return
   */
  private Response<ServiceInstance> getRoundRobinInstance(List<ServiceInstance> serviceInstances) {
    if (CollectionUtils.isEmpty(serviceInstances)) {
      log.warn("There are not instance available {}", serviceId);
      return new EmptyResponse();
    }
    // position +1
    int pos = Math.abs(this.position.incrementAndGet());
    // 取模
    ServiceInstance serviceInstance = serviceInstances.get(pos % serviceInstances.size());
    return new DefaultResponse(serviceInstance);
  }

}
