package com.module.userservice.restTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Slf4j
@RequiredArgsConstructor
@Component
public class RestCall {

    private final UrlServiceInerface serviceInerface;
    private final RestTemplate restTemplate;


    @SneakyThrows
    public BaseResponse call(HttpMethod method, String serviceName, String url, String apiVersion, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        String serviceUrl = serviceInerface.getServicesUrl(serviceName);
        String fullUrl = serviceUrl + "/" + apiVersion + url;
        ResponseEntity<BaseResponse> respone = null;
        try {
            respone = restTemplate.exchange(fullUrl, method, entity, BaseResponse.class);
        } catch (HttpClientErrorException e) {
            ErrorResponse error = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            System.out.println(error.getMessage());
            // Now you have a model of the error response and can handle it appropriately.
        }

        return respone.getBody();
    }
}
