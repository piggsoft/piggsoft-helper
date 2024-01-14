package com.piggsoft.spring.boot.starter.helper.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private int code;
    private String message;
    private T data;

}
