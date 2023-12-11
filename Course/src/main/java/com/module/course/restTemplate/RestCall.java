package com.module.course.restTemplate;


import com.module.course.ApiVersion;
import com.module.course.dto.BaseResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;


@Slf4j
@RequiredArgsConstructor
@Component
public class RestCall {

    private final UrlServiceInerface serviceInerface;
    private final RestTemplate restTemplate;

    public BaseResponse call(HttpMethod method, String serviceName, String url, String apiVersion, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        String serviceUrl = serviceInerface.getServicesUrl(serviceName);
        String fullUrl = serviceUrl + "/" + apiVersion + url;

        return restTemplate.exchange(fullUrl, method, entity, BaseResponse.class).getBody();
    }
}
