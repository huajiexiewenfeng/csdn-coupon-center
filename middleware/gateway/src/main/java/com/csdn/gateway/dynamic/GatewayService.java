package com.csdn.gateway.dynamic;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

/**
 * @Author: xiewenfeng
 * @Date: 2022/3/7 14:55
 */
@Slf4j
@Service
public class GatewayService {

  @Autowired
  private RouteDefinitionWriter routeDefinitionWriter;

  @Autowired
  private ApplicationEventPublisher publisher;

  public void updateRoutes(List<RouteDefinition> routes) {
    if (CollectionUtils.isEmpty(routes)) {
      log.warn("No route found");
      return;
    }
    routes.forEach(route -> {
      try {
        routeDefinitionWriter.save(Mono.just(route)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
      } catch (Exception e) {
        log.error("cannot update route,id={}", route.getId());
      }
    });
  }

}
