package com.csdn.coupon.template;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SentinelOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        log.info("request {}, header={}", request.getParameterMap(), request.getHeaderNames());
        return request.getHeader("SentinelSource");
    }
}
