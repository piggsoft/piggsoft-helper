package com.piggsoft.spring.boot.starter.helper.apiversion.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersions {
    ApiVersion[] value() default {};
}
