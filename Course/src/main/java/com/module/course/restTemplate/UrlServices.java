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
public class UrlServices implements UrlServiceInerface{

    private final DiscoveryClient discoveryClient;

    @Override
    public String getServicesUrl(String locate) {
        List<ServiceInstance> instances = discoveryClient.getInstances(locate);

        if (instances.isEmpty()) {
            throw new RuntimeException("No instances for courseServiceId: " + locate);
        }
        // Lấy instance đầu tiên
        ServiceInstance instance = instances.get(0);

        // Trả về địa chỉ của dịch vụ
        return instance.getUri().toString();
    }

//    private String getServicesId(String locate) {
//        switch (locate) {
//            case "user":
//                return userServiceId;
//            default:
//                return null;
//        }
//    }
}
