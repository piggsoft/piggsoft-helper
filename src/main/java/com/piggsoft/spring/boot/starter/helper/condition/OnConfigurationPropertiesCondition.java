package com.piggsoft.spring.boot.starter.helper.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.List;
import java.util.stream.Collectors;


public class OnConfigurationPropertiesCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        List<AnnotationAttributes> allAnnotationAttributes = metadata.getAnnotations()
                .stream(ConditionalOnConfigurationProperties.class.getName())
                .filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes))
                .map(MergedAnnotation::asAnnotationAttributes)
                .collect(Collectors.toList());
        for (AnnotationAttributes attributes : allAnnotationAttributes) {
            Class<?> propertiesClass = attributes.getClass("propertiesClass");

//            if (null != context.getBeanFactory()) {
//                Object bean1 = context.getBeanFactory().getBean(propertiesClass);
//                System.out.println(bean1);
//            }
            Object bean = Binder.get(context.getEnvironment()).bind("piggsoft.helper.web", propertiesClass).orElse(null);

            System.out.println(bean);
        }
        return null;
    }
}
