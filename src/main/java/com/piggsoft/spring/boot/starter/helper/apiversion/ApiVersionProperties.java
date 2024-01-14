package com.piggsoft.spring.boot.starter.helper.apiversion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("piggsoft.helper.apiversion")
@Data
public class ApiVersionProperties {

    private String placeholder = "{version}";

    private String quoteLeft = "{";

    private String quoteRight = "}";

}
