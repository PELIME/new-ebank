package com.pelime.ebanck.server.api;

import com.pelime.ebanck.server.support.EbanckCode;
import com.pelime.ebanck.server.support.EbanckHttpEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @RequestMapping(value = "/userinfo",produces = "application/json;charset=UTF-8")
    public Object userinfo(){
        Object result= SecurityUtils.getSubject().getPrincipal();
        return new EbanckHttpEntity(EbanckCode.SUCCESS,result);
    }
}
