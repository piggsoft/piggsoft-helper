package com.piggsoft.spring.boot.starter.helper.web;


import com.piggsoft.spring.boot.starter.helper.condition.ConditionalOnConfigurationProperties;
import com.piggsoft.spring.boot.starter.helper.web.response.parser.ExceptionParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

@Configuration
@ConditionalOnProperty(value = "piggsoft.helper.web.enable", matchIfMissing = true)
@EnableConfigurationProperties(WebProperties.class)
public class WebConfiguration {

    @Bean
    public ExceptionParser exceptionParser() {
        return new ExceptionParser(new HashMap<>());
    }

    @Configuration
    @Import(WebExceptionHandler.class)
    @ConditionalOnProperty(value = "piggsoft.helper.web.exceptionHandler.enable", matchIfMissing = true)
    //@ConditionalOnConfigurationProperties(propertiesClass = WebProperties.class, prefix = WebProperties.PREFIX)
    public static class WebExceptionConfiguration {

    }

}
