package com.pelime.ebanck.server.shiro;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OptionFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin","*");
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            response.addHeader("Access-Control-Allow-Methods","GET,HEAD,POST,PUT,DELETE,TRANCE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers","Content-Type,Accept,Authorization");
        }
        return true;
    }
}
