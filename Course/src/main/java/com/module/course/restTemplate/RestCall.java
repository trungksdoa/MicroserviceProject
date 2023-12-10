package com.module.course.restTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.userservice.enums.HttpMethods;
import com.module.userservice.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Slf4j
@RequiredArgsConstructor
public class RestCall {

    private final String baseUrl;
    @SneakyThrows
    public BaseResponse doCall(HttpMethods method, String forwardUrl) {
        HttpRequest request = buildRequest(method, forwardUrl, null);
        return sendRequest(request);
    }

    @SneakyThrows
    public BaseResponse doCall(HttpMethods method, String forwardUrl, Object data) {
        HttpRequest request = buildRequest(method, forwardUrl, data);
        return sendRequest(request);
    }

    private HttpRequest buildRequest(HttpMethods method, String forwardUrl, Object data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment("api", "v1", forwardUrl)
                .toUriString();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URL(uri).toURI())
                .method(method.name(), data == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(data)));

        if (data != null) {
            requestBuilder.header("Content-Type", "application/json");
        }

        return requestBuilder.build();
    }

    @SneakyThrows
    private BaseResponse sendRequest(HttpRequest request) {
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new ObjectMapper().readValue(response.body(), BaseResponse.class);
    }

}
