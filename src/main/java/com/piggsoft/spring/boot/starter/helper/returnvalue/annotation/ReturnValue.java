package com.piggsoft.spring.boot.starter.helper.returnvalue.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnValue {
    boolean exclude() default false;
}
