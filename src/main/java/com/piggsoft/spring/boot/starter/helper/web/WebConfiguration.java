package com.piggsoft.spring.boot.starter.helper.web;


import com.piggsoft.spring.boot.starter.helper.web.response.parser.ExceptionParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class WebConfiguration {

    @Bean
    public WebExceptionHandler webExceptionHandler() {
        return new WebExceptionHandler(new ExceptionParser(new HashMap<>()));
    }

}
