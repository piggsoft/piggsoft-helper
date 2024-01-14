package com.piggsoft.spring.boot.starter.helper.web.response.parser;


import com.piggsoft.spring.boot.starter.helper.web.WebException;
import com.piggsoft.spring.boot.starter.helper.web.response.Response;
import com.piggsoft.spring.boot.starter.helper.web.response.ResponseBuilder;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class ExceptionParser {

    private String codeMessageSpilt = ":";
    private static final String CODE_MESSAGE_SPILT_KEY = "_codeMessageSpilt_";

    private String customMessageSymbol = "?";
    private static final String CUSTOM_MESSAGE_SYMBOL_KEY = "_customMessageSymbol_";

    private final Map<String, String> exceptionResponseMappings;

    public ExceptionParser(Map<String, String> exceptionResponseMappings) {
        this.exceptionResponseMappings = exceptionResponseMappings;
        if (this.exceptionResponseMappings != null) {
            if (this.exceptionResponseMappings.containsKey(CODE_MESSAGE_SPILT_KEY)) {
                codeMessageSpilt = exceptionResponseMappings.get(CODE_MESSAGE_SPILT_KEY);
            }
            if (this.exceptionResponseMappings.containsKey(CUSTOM_MESSAGE_SYMBOL_KEY)) {
                customMessageSymbol = exceptionResponseMappings.get(CUSTOM_MESSAGE_SYMBOL_KEY);
            }
        }
    }

    public <T> Response<T> parse(WebException e) {
        return ResponseBuilder.create()
                .code(e.getCode())
                .message(e.getInnerMessage())
                .build();
    }

    public <T> Response<T> parse(Exception e) {
        return parse(e, null, null);
    }

    public <T> Response<T> parse(Exception e, String exceptionMessage) {
        return parse(e, exceptionMessage, null);
    }

    public <T> Response<T> parse(Exception e, String exceptionMessage, T data) {
        String mappingKey = e.getClass().getName();
        if (!this.exceptionResponseMappings.containsKey(mappingKey)) {
            if (!ObjectUtils.isEmpty(exceptionMessage)) {
                return ResponseBuilder.unknownError(exceptionMessage);
            } else {
                return ResponseBuilder.unknownError();
            }
        }
        String original = exceptionResponseMappings.get(mappingKey);
        final String[] codeAndMessage = original.split(this.codeMessageSpilt);
        int code = Integer.parseInt(codeAndMessage[0]);
        String message = codeAndMessage[1];

        if (exceptionMessage != null && this.customMessageSymbol.equals(message)) {
            message = exceptionMessage;
        }

        return ResponseBuilder.create()
                .code(code)
                .message(message)
                .build(data);
    }


}
