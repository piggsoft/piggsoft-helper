package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableConfigurationProperties(ApiVersionProperties.class)
public class ApiVersionConfiguration {

    @Bean
    public WebMvcRegistrations webMvcRegistrations(ApiVersionProperties apiVersionProperties) {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new ApiVersionRequestMappingHandlerMapping(apiVersionProperties);
            }
        };
    }

}
