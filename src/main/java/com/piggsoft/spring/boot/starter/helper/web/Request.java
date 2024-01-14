package com.piggsoft.spring.boot.starter.helper.web;

import lombok.Data;

@Data
public class Request {

    private String requestTime;
    private String serialNo;
    private String apiVersion;
    private String clientType;
    private String clientVersion;

}
