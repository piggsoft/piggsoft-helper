package com.piggsoft.spring.boot.starter.helper.apiversion;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public interface ConditionFactory {

    RequestCondition<?> create(String[] paths);

}
