package com.piggsoft.spring.boot.starter.helper.common;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class StringTools {


    public static String joinPropertiesPrefix(String... prefixs) {
        return join('.', prefixs);
    }

    public static String join(char join, String... strings) {
        return Strings.join(Arrays.stream(strings).iterator(), join);
    }


}
