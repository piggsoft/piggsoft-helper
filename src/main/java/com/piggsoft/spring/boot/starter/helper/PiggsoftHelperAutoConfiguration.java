package com.piggsoft.spring.boot.starter.helper;

import com.piggsoft.spring.boot.starter.helper.apiversion.ApiVersionConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(value = "enable", prefix = "piggsoft.helper", matchIfMissing = true)
@Import({ApiVersionConfiguration.class})
public class PiggsoftHelperAutoConfiguration {
}
