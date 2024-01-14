package com.piggsoft.spring.boot.starter.helper.web.response;

public class ResponseBuilder {

    private static final int DEFAULT_SUCCESS_CODE = 0;
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final int UNKNOWN_ERROR_CODE = -1;
    private static final String UNKNOWN_ERROR_MESSAGE = "system error";


    private int code = 0;
    private String message = "success";

    ResponseBuilder() {
    }

    public static ResponseBuilder create() {
        return new ResponseBuilder();
    }

    public static ResponseBuilder fromCode(int code) {
        ResponseBuilder builder = new ResponseBuilder();
        builder.code = code;
        return builder;
    }

    public static ResponseBuilder fromMessage(String message) {
        ResponseBuilder builder = new ResponseBuilder();
        builder.message = message;
        return builder;
    }


    public static <T> Response<T> unknownError() {
        return unknownError(UNKNOWN_ERROR_MESSAGE);
    }

    public static <T> Response<T> unknownError(String message) {
        return create()
                .code(UNKNOWN_ERROR_CODE)
                .message(message)
                .build();
    }

    public static <T> Response<T> emptySuccess() {
        return success(null);
    }

    public static <T> Response<T> success(T t) {
        return create()
                .code(DEFAULT_SUCCESS_CODE)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .build(t);
    }

    public <T> Response<T> build() {
        return build(null);
    }

    public <T> Response<T> build(T t) {
        return new Response<>(code, message, t);
    }

    public ResponseBuilder code(int code) {
        this.code = code;
        return this;
    }

    public ResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

}
