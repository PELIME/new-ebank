package com.pelime.ebanck.server.api;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
public class UserController {
    @RequestMapping(value = "/userinfo")
    public Object userinfo(){
        Object result= SecurityUtils.getSubject().getPrincipal();
        return result;
    }
}
