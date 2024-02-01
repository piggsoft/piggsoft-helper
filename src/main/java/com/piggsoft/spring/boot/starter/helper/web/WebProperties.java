package com.piggsoft.spring.boot.starter.helper.web;

import com.piggsoft.spring.boot.starter.helper.condition.ConditionalProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.piggsoft.spring.boot.starter.helper.common.Constants.PROPERTIES_BASE_PREFIX;

@Data
@ConfigurationProperties(WebProperties.PREFIX)
public class WebProperties implements ConditionalProperties {

    public static final String PREFIX = PROPERTIES_BASE_PREFIX + ".web";


    private Exception exception = new Exception();

    @Override
    public boolean match() {
        return false;
    }

    @Data
    public static class Exception {

        private Handler handler = new Handler();

        @Data
        public static class Handler {
            private boolean enable = true;
        }

    }

}
