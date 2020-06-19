package com.pelime.ebanck.server.dao;

import com.pelime.ebanck.server.domain.MessageLinkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLinkRecordDao extends JpaRepository<MessageLinkRecord,Long> {
}
