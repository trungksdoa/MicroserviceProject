package com.module.userservice.restTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServices implements UrlServiceInerface{

    private final DiscoveryClient discoveryClient;

//    @Value("${service.user.id}")
//    private String userServiceId;


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

}
