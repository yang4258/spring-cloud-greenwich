package com.yang.userview.controller;

import com.yang.userview.entity.User;
import com.yang.userview.rpc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author: yang
 * @date: 2020/10/15
 * Description: 调用服务接口
 */
@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserService userService;

    /**获取所有服务提供者 */
    @GetMapping("/instances-lists")
    public Object instancesLists() {
        return discoveryClient.getInstances("user-service");
    }

    /** 获取所有注册服务名称     */
    @GetMapping("/services-lists")
    public Object servicesLists() {
        return discoveryClient.getServices();
    }


    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @GetMapping("/poll-service")
    public Object pollService() {
        return loadBalancer.choose("user-service").getUri().toString();
    }

    /**
     * 调用服务提供者接口
     */
    @GetMapping("/hello")
    public String hello() {
        ServiceInstance serviceInstance = loadBalancer.choose("user-service");
        URI uri = serviceInstance.getUri();
        String callService = new RestTemplate().getForObject(uri + "/hello", String.class);
        System.out.println(callService);
        return callService;
    }

    /**
     * 调用服务提供者接口
     */
    @GetMapping("/hello2")
    public String hello2() {
        String hello = userService.hello();
        return hello;
    }

    @GetMapping("/findById")
    public User findById() {
        User user = userService.findById("123");
        return user;
    }

    @GetMapping("/find")
    public User find() {
        User user1 = new User();
        user1.setId("asd");
        user1.setName("测试对象接收");
        User user = userService.find(user1);
        return user;
    }
}