package com.piggsoft.spring.boot.starter.helper.returnvalue;

import com.piggsoft.spring.boot.starter.helper.returnvalue.annotation.ReturnValue;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler delegate;
    private ReturnValueTransform transformer;

    public HandlerMethodReturnValueHandlerProxy(
            HandlerMethodReturnValueHandler delegate,
            ReturnValueTransform transformer
    ) {
        this.delegate = delegate;
        this.transformer = transformer;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Executable executable = returnType.getExecutable();
        if (!(executable instanceof Method)) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            return;
        }
        Method method = (Method) executable;
        Class<?> returnTypeClass = method.getReturnType();

        ReturnValue annotation = AnnotationUtils.findAnnotation(method, ReturnValue.class);
        if (annotation != null && annotation.exclude()) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            return;
        }

        if (returnValue == null || Void.class.equals(returnTypeClass)) {
            delegate.handleReturnValue(transformer.transform(null), returnType, mavContainer, webRequest);
        } else {
            delegate.handleReturnValue(transformer.transform(returnValue), returnType, mavContainer, webRequest);
        }
    }

}
