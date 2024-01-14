package com.piggsoft.spring.boot.starter.helper.returnvalue;

import com.piggsoft.spring.boot.starter.helper.web.response.Response;
import com.piggsoft.spring.boot.starter.helper.web.response.ResponseBuilder;

public class ResponseReturnValueTransform implements ReturnValueTransform {
    @Override
    public Object transform(Object source) {
        if (source != null && source.getClass().equals(Response.class)) {
            return source;
        } else {
            return ResponseBuilder.success(source);
        }
    }
}
