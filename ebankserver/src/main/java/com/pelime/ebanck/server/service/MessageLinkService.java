package com.pelime.ebanck.server.service;

import com.pelime.ebanck.server.dao.MessageLinkDao;
import com.pelime.ebanck.server.dao.MessageLinkRecordDao;
import com.pelime.ebanck.server.dao.MessageModelDao;
import com.pelime.ebanck.server.domain.MessageLink;
import com.pelime.ebanck.server.domain.MessageLinkRecord;
import com.pelime.ebanck.server.domain.MessageModel;
import com.pelime.ebanck.server.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageLinkService {
    @Autowired
    MessageLinkDao messageLinkDao;

    @Autowired
    MessageLinkRecordDao messageLinkRecordDao;

    @Autowired
    MessageModelDao messageModelDao;

    public MessageLink saveMessageLink(String phones, String body){
        MessageLink messageLink=new MessageLink();
        User user= (User)SecurityUtils.getSubject().getPrincipal();
        messageLink.setId(user.getId());
        messageLink.setUserId(user.getId());
        messageLink.setPhones(phones);
        messageLink.setBody(body);
        messageLink.setFlag(System.currentTimeMillis()+"");
        messageLinkDao.save(messageLink);
        messageLinkRecordDao.save(new MessageLinkRecord(messageLink));
        return messageLink;
    }

    public MessageLink getLink(){
        User user= (User)SecurityUtils.getSubject().getPrincipal();
        return messageLinkDao.findById(user.getId()).get();
    }

    public List<MessageModel> getDisplayMessageModel(){
        return messageModelDao.findAllByDisplayIsTrue();
    }
}
