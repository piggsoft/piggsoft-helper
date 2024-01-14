package com.piggsoft.spring.boot.starter.helper.returnvalue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("piggsoft.helper.returnvalue")
@Data
public class ReturnValueProperties {

    private boolean isDefaultReturnValueHandler = true;

}
