package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.util.pattern.PathPatternParser;

public class PathPatternsRequestConditionFactory implements ConditionFactory {
    @Override
    public RequestCondition<?> create(String[] paths) {
        return new PathPatternsRequestCondition(PathPatternParser.defaultInstance, paths);
    }
}
