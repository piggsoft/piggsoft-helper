package com.piggsoft.spring.boot.starter.helper.apiversion;

import com.piggsoft.spring.boot.starter.helper.apiversion.annotation.ApiVersion;
import com.piggsoft.spring.boot.starter.helper.apiversion.annotation.ApiVersions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private ApiVersionProperties apiVersionProperties;
    private ConditionFactory conditionFactory;


    public ApiVersionRequestMappingHandlerMapping(ApiVersionProperties apiVersionProperties, ConditionFactory conditionFactory) {
        this.apiVersionProperties = apiVersionProperties;
        this.conditionFactory = conditionFactory;
    }


    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersions apiVersions = AnnotationUtils.findAnnotation(handlerType, ApiVersions.class);
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        if (apiVersions == null && apiVersion == null) {
            return super.getCustomTypeCondition(handlerType);
        }

        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(handlerType, RequestMapping.class);

        if (apiVersions != null) {
            return createRequestCondition(requestMapping, null, apiVersions);
        }

        return createRequestCondition(requestMapping, null, apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        Class<?> methodClass = method.getDeclaringClass();

        ApiVersions apiVersions = AnnotationUtils.findAnnotation(method, ApiVersions.class);
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (apiVersions == null && apiVersion == null) {
            return super.getCustomMethodCondition(method);
        }

        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(methodClass, RequestMapping.class);
        RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);


        if (apiVersions != null) {
            return createRequestCondition(classMapping, methodMapping, apiVersions);
        }

        return createRequestCondition(classMapping, methodMapping, apiVersion);
    }

    protected RequestCondition<?> createRequestCondition(RequestMapping classMapping, RequestMapping methodMapping, ApiVersions apiVersions) {
        return createRequestCondition(classMapping, methodMapping, apiVersions.value());
    }

    protected RequestCondition<?> createRequestCondition(RequestMapping classMapping, RequestMapping methodMapping, ApiVersion... apiVersions) {
        if (apiVersions == null || apiVersions.length < 1) {
            return null;
        }


        List<String> paths = null;
        if (classMapping == null && methodMapping == null) {
            return null;
        } else if (methodMapping == null) {
            String[] classPaths = classMapping.value();
            paths = Stream.of(classPaths).collect(Collectors.toList());
        } else if (classMapping == null) {
            String[] methodPaths = methodMapping.value();
            paths = Stream.of(methodPaths).collect(Collectors.toList());
        } else {
            String[] classPaths = classMapping.value();
            String[] methodPaths = methodMapping.value();
            paths = Arrays.stream(classPaths).flatMap(classPath -> Arrays.stream(methodPaths).map(methodPath -> classPath + methodPath)).collect(Collectors.toList());
        }

        List<String> apiVersionValues = Arrays.stream(apiVersions).map(ApiVersion::value).collect(Collectors.toList());

        String[] fullPaths = paths.stream().flatMap(path -> apiVersionValues.stream().map(apiVersion -> this.replacePlaceholder(path, apiVersion))).toArray(String[]::new);

        /* return new PatternsRequestCondition(fullPaths);*/
        return conditionFactory.create(fullPaths);
    }


    protected String replacePlaceholder(String text, String apiVersion) {
        if (ObjectUtils.isEmpty(text)) {
            return text;
        }
        int startIndex = text.indexOf(apiVersionProperties.getQuoteLeft());
        if (startIndex == -1) {
            return text;
        }
        int endIndex = text.indexOf(apiVersionProperties.getQuoteRight());
        if (endIndex == -1) {
            return text;
        }

        String startString = text.substring(0, startIndex);
        String endString = text.substring(endIndex + 1);

        return startString + apiVersion + endString;
    }
}
