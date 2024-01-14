package com.piggsoft.spring.boot.starter.helper.web;

import lombok.Getter;

@Getter
public class WebException extends RuntimeException {

    private final int code;

    public WebException(int code, String message) {
        super(message);
        this.code = code;
    }

    public WebException(int code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return "code: " + this.code + ", message: " + super.getMessage();
    }

    public String getInnerMessage() {
        return getMessage();
    }


}
