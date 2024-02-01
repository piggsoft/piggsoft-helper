package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class PatternsRequestConditionFactory implements ConditionFactory {

    private WebMvcProperties webMvcProperties;

    public PatternsRequestConditionFactory(WebMvcProperties webMvcProperties) {
        if (WebMvcProperties.MatchingStrategy.ANT_PATH_MATCHER != webMvcProperties.getPathmatch().getMatchingStrategy()) {
            webMvcProperties.getPathmatch().setMatchingStrategy(WebMvcProperties.MatchingStrategy.ANT_PATH_MATCHER);
        }
    }

    @Override
    public RequestCondition<?> create(String[] paths) {
        return new PatternsRequestCondition(paths);
    }

}
