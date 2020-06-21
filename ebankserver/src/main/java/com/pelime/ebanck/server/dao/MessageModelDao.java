package com.pelime.ebanck.server.dao;

import com.pelime.ebanck.server.domain.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageModelDao extends JpaRepository<MessageModel,Integer> {
    List<MessageModel> findAllByDisplayIsTrue();
}
