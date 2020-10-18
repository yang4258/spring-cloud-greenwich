package com.yang.userview.rpc;

import com.yang.userview.config.FeginConfig;
import com.yang.userview.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", configuration = FeginConfig.class)
public interface UserService {

    @RequestMapping(name = "/hello", method = RequestMethod.POST)
    String hello();

    @RequestMapping("/findById")
    @ResponseBody
    User findById(@RequestParam("id") String id);

    @RequestMapping("/find")
    @ResponseBody
    User find(@RequestBody User user);

}
