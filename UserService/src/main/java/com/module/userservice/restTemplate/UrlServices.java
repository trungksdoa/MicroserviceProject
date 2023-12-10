package com.module.userservice.restTemplate;

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

    @Value("${service.course.id}")
    private String courseServiceId;

    public String getServicesUrl(String locate) {
        String servicesId = getServicesId(locate);
        List<ServiceInstance> instances = discoveryClient.getInstances(servicesId);

        if (instances.isEmpty()) {
            throw new RuntimeException("No instances for courseServiceId: " + servicesId);
        }
        // Lấy instance đầu tiên
        ServiceInstance instance = instances.get(0);

        // Trả về địa chỉ của dịch vụ
        return instance.getUri().toString();
    }

    private String getServicesId(String locate) {
        return switch (locate) {
            case "course" -> courseServiceId;
            default -> null;
        };
    }
}
