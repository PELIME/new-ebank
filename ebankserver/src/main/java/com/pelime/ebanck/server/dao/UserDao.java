package com.pelime.ebanck.server.dao;


import com.pelime.ebanck.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByPhone(String phone);
    User findByEmail(String email);


}
