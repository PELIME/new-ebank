package com.pelime.ebanck.server.api;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @RequestMapping(value = "/userinfo",produces = "application/json;charset=UTF-8")
    public Object userinfo(){
        Object result= SecurityUtils.getSubject().getPrincipal();
        return result;
    }
}
