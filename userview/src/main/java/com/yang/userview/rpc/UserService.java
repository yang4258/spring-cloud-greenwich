package com.yang.userview.rpc;

import com.yang.userview.config.FeginConfig;
import com.yang.userview.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", configuration = FeginConfig.class, fallback = UserService.Fallback.class)
@Primary
public interface UserService {

    @RequestMapping(name = "/hello")
    String hello();

    @RequestMapping("/findById")
    @ResponseBody
    User findById(@RequestParam("id") String id);

    @RequestMapping("/find")
    @ResponseBody
    User find(@RequestBody User user);

    @Component
    class Fallback implements UserService {

        @Override
        public String hello() {
            return "熔断返回";
        }

        @Override
        public User findById(String id) {
            User user = new User();
            user.setName("熔断返回");
            return user;
        }

        @Override
        public User find(User user) {
            user.setName("熔断返回");
            return user;
        }
    }
}
