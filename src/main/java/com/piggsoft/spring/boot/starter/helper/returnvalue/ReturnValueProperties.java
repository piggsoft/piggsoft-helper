package com.piggsoft.spring.boot.starter.helper.returnvalue;

import com.piggsoft.spring.boot.starter.helper.common.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

@ConfigurationProperties("piggsoft.helper.returnvalue")
@Data
public class ReturnValueProperties {

    //Stream.of(Constants.PROPERTIES_BASE_PREFIX, "returnvalue")

    public static final String PREFIX = Constants.PROPERTIES_BASE_PREFIX + ".returnvalue";

    private boolean isDefaultReturnValueHandler = true;

}
