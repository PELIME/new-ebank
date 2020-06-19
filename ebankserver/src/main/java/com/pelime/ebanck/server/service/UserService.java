package com.pelime.ebanck.server.service;

import com.pelime.ebanck.server.dao.UserDao;
import com.pelime.ebanck.server.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User register(User user){
        return userDao.save(user);
    }
}
