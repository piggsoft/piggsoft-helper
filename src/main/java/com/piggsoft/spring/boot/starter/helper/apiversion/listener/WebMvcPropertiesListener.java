package com.piggsoft.spring.boot.starter.helper.apiversion.listener;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class WebMvcPropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String path_matcher_key1 = "spring.mvc.pathmatch.matching-strategy";
    private static final String path_matcher_key2 = "spring.mvc.pathmatch.matchingStrategy";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        /*ConfigurableEnvironment environment = event.getEnvironment();
        String value = environment.getProperty(path_matcher_key1);
        if (value == null) {
            environment.getProperty(path_matcher_key2);
        }*/
        /*String currentValue = WebMvcProperties.MatchingStrategy.ANT_PATH_MATCHER.name();
        if (value != null && !currentValue.equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("spring.mvc.pathmatch.matchingStrategy 必须是 " + currentValue);
        }*/
       /* if (value == null) {
            Map<String, Object> map = new HashMap<>();
            map.put(path_matcher_key1, currentValue);
            environment.getPropertySources().addFirst(new MapPropertySource(WebMvcPropertiesListener.class.getName(), map));
        }*/
    }

}
