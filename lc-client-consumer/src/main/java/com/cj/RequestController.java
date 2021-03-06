package com.cj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Configuration
public class RequestController {


    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/ask")
    public String ask(){
       return restTemplate.getForEntity("http://EUREKA-CLIENT-SERVICE/ask/{name}", String.class, name).getBody();
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstanceList(@PathVariable String applicationName) {
       return discoveryClient.getInstances(applicationName);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
