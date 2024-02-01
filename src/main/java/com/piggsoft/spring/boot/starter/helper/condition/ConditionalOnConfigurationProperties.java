package com.piggsoft.spring.boot.starter.helper.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnConfigurationPropertiesCondition.class)
public @interface ConditionalOnConfigurationProperties {

    String prefix();

    Class<? extends  ConditionalProperties> propertiesClass();

    boolean matchIfMissing() default true;
}
