package com.module.course.restTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServices {

    private final DiscoveryClient discoveryClient;

    @Value("${service.user.id}")
    private String userServiceId;

    public String getServicesUrl(String locate) {
        List<ServiceInstance> instances = discoveryClient.getInstances(getServicesId(locate));

        if (instances.isEmpty()) {
            throw new RuntimeException("No instances for courseServiceId: " + userServiceId);
        }
        // Lấy instance đầu tiên
        ServiceInstance instance = instances.get(0);

        // Trả về địa chỉ của dịch vụ
        return instance.getUri().toString();
    }

    private String getServicesId(String locate) {
        switch (locate) {
            case "user":
                return userServiceId;
            default:
                return null;
        }
    }
}
