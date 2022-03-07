package com.csdn.gateway.dynamic;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>1.项目首次启动，从 nacos 读取配置文件。初始化路由表<p/>
 * <p>2.当 nacos 配置发生变化，监听变化并更新路由表<p/>
 *
 * @Author: xiewenfeng
 * @Date: 2022/3/7 15:11
 */
@Component
public class DynamicRoutesLoader implements InitializingBean {

  @Autowired
  private NacosConfigManager configService;

  @Autowired
  private NacosConfigProperties properties;

  @Autowired
  private DynamicRoutesListener listener;

  private static final String ROUTES_CONFIG = "routes-config.json";

  @Override
  public void afterPropertiesSet() throws Exception {
    // 首次加载配置项
    String config = configService.getConfigService()
        .getConfig(ROUTES_CONFIG, properties.getGroup(), 10000);
    listener.receiveConfigInfo(config);
    // 注册监听器
    configService.getConfigService().addListener(ROUTES_CONFIG, properties.getGroup(), listener);
  }
}
