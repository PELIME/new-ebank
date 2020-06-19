package com.pelime.ebanck.server.dao;


import com.pelime.ebanck.server.domain.MessageLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLinkDao extends JpaRepository<MessageLink,Long> {

}
