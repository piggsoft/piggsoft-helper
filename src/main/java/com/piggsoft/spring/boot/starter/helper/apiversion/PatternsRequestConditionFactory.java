package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class PatternsRequestConditionFactory implements ConditionFactory, EnvironmentAware {

    private static final String path_matcher_key1 = "spring.mvc.pathmatch.matching-strategy";
    private static final String path_matcher_key2 = "spring.mvc.pathmatch.matchingStrategy";

    @Override
    public RequestCondition<?> create(String[] paths) {
        return new PatternsRequestCondition(paths);
    }


    @Override
    public void setEnvironment(Environment environment) {
        String value = environment.getProperty(path_matcher_key1);
        if (value == null) {
            value = environment.getProperty(path_matcher_key2);
        }
        String currentValue = WebMvcProperties.MatchingStrategy.ANT_PATH_MATCHER.name();
        if (!currentValue.equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("spring.mvc.pathmatch.matchingStrategy 必须是 " + currentValue);
        }
    }
}
