package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableConfigurationProperties(ApiVersionProperties.class)
@ConditionalOnProperty(value = "piggsoft.helper.apiversion.enable", matchIfMissing = true)
public class ApiVersionConfiguration {


    @ConditionalOnClass(PathPatternsRequestCondition.class)
    @Bean
    public PathPatternsRequestConditionFactory pathPatternsRequestCondition() {
        return new PathPatternsRequestConditionFactory();
    }

    @ConditionalOnMissingClass("org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition")
    @Bean
    public PatternsRequestConditionFactory patternsRequestConditionFactory() {
        return new PatternsRequestConditionFactory();
    }

    @Bean
    public WebMvcRegistrations webMvcRegistrations(ApiVersionProperties apiVersionProperties, ConditionFactory conditionFactory) {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new ApiVersionRequestMappingHandlerMapping(apiVersionProperties, conditionFactory);
            }
        };
    }

}
