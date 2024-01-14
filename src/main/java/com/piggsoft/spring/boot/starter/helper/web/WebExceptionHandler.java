package com.piggsoft.spring.boot.starter.helper.web;

import com.piggsoft.spring.boot.starter.helper.web.response.Response;
import com.piggsoft.spring.boot.starter.helper.web.response.ResponseBuilder;
import com.piggsoft.spring.boot.starter.helper.web.response.parser.ExceptionParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    private final ExceptionParser exceptionParser;

    public WebExceptionHandler(ExceptionParser exceptionParser) {
        this.exceptionParser = exceptionParser;
    }


    @ExceptionHandler
    @ResponseBody
    public Response<Object> webException(WebException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.create().code(e.getCode()).message(e.getInnerMessage()).build();
    }

    @ExceptionHandler
    @ResponseBody
    public Response<Object> bindException(BindException e) {
        log.error(e.getMessage(), e);
        return parseBindResult(e);
    }

    @ExceptionHandler
    @ResponseBody
    public Response<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return parseBindResult(e);
    }

    private Response<Object> parseBindResult(BindException e) {
        if (e == null) {
            return ResponseBuilder.unknownError();
        }
        final BindingResult br = e.getBindingResult();
        final FieldError fieldError = br.getFieldError();
        if (fieldError == null) {
            return ResponseBuilder.unknownError();
        }
        return exceptionParser.parse(e, fieldError.getField() + ":" + fieldError.getDefaultMessage());
    }

    @ExceptionHandler
    @ResponseBody
   public Response<Object> exception(Exception e) {
       log.error(e.getMessage(), e);
       return exceptionParser.parse(e);
   }

}