package com.pelime.ebanck.server.service;

import com.pelime.ebanck.server.dao.RoleDao;
import com.pelime.ebanck.server.dao.UserDao;
import com.pelime.ebanck.server.domain.User;
import com.pelime.ebanck.server.support.StaticCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AuthenticationService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    public User dbuser(String principals){
        User user=null;
        //有限的正则
        if(principals.matches(StaticCommon.PHONE_REGEX)){
            user=userDao.findByPhone(principals);

        }else if(principals.matches(StaticCommon.MAIL_REGEX)){
            user=userDao.findByEmail(principals);
        }
        else
            user=userDao.findByUsername(principals);
        return user;
    }

    public User register(String principals,String password){
        User user=dbuser(principals);
        if(user!=null) return null;
        //有限的正则
        if(principals.matches(StaticCommon.PHONE_REGEX)){
            user=new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setPhone(principals);
            user.setPassword(StaticCommon.MD5Pwd(principals,password));
        }else if(principals.matches(StaticCommon.MAIL_REGEX)){
            user=new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setEmail(principals);
            user.setPassword(StaticCommon.MD5Pwd(principals,password));

        }
        else{
            user=new User();
            user.setUsername(principals);
            user.setPassword(StaticCommon.MD5Pwd(principals,password));
        }
        return user;
    }
}
