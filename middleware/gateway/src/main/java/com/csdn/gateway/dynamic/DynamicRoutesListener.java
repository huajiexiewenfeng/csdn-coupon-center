package com.csdn.gateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import java.util.List;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: xiewenfeng
 * @Date: 2022/3/7 15:01
 */
@Slf4j
@Component
public class DynamicRoutesListener implements Listener {

  @Autowired
  private GatewayService service;

  @Override
  public Executor getExecutor() {
    log.info("getExecutor");
    return null;
  }

  @Override
  public void receiveConfigInfo(String configInfo) {
    log.info("received routes changes {}", configInfo);
    List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
    service.updateRoutes(routeDefinitions);
  }
}
