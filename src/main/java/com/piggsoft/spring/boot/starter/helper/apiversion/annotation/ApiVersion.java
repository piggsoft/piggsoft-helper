package com.piggsoft.spring.boot.starter.helper.apiversion.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ApiVersions.class)
public @interface ApiVersion {
    String value() default "*";
}
