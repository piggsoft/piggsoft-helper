package com.piggsoft.spring.boot.starter.helper.returnvalue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(ReturnValueProperties.class)
public class ReturnValueConfiguration implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private List<HandlerMethodReturnValueHandler> proxyHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        if (ObjectUtils.isEmpty(returnValueHandlers)) {
            return returnValueHandlers;
        }

        return returnValueHandlers.stream().map(e -> {
            if (e instanceof RequestResponseBodyMethodProcessor) {
                return createPiggsoftHandlerMethodReturnValueHandlerProxy(e);
            } else {
                return e;
            }
        }).collect(Collectors.toList());

    }

    public HandlerMethodReturnValueHandlerProxy createPiggsoftHandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler handlerMethodReturnValueHandler) {
        return new HandlerMethodReturnValueHandlerProxy(handlerMethodReturnValueHandler, new ResponseReturnValueTransform());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        requestMappingHandlerAdapter.setReturnValueHandlers(
                proxyHandlers(requestMappingHandlerAdapter.getReturnValueHandlers())
        );
    }
}
