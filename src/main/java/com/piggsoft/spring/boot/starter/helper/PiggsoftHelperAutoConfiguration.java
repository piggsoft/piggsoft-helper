package com.piggsoft.spring.boot.starter.helper;

import com.piggsoft.spring.boot.starter.helper.apiversion.ApiVersionConfiguration;
import com.piggsoft.spring.boot.starter.helper.returnvalue.ReturnValueConfiguration;
import com.piggsoft.spring.boot.starter.helper.web.WebConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(value = "enable", prefix = "piggsoft.helper", matchIfMissing = true)
@Import({
        WebConfiguration.class,
        ApiVersionConfiguration.class,
        ReturnValueConfiguration.class
})
public class PiggsoftHelperAutoConfiguration {
}
