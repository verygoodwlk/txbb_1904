package com.qf.serviceapi;

import com.qf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TXBB-USER")
@Component
public interface UserServiceApi {

    @RequestMapping("/user/queryUser")
    User queryUserByUid(@RequestParam("uid") Integer uid);
}
