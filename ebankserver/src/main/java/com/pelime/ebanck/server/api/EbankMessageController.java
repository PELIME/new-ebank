package com.pelime.ebanck.server.api;

import com.pelime.ebanck.server.domain.MessageLink;
import com.pelime.ebanck.server.service.MessageLinkService;
import com.pelime.ebanck.server.support.EbanckCode;
import com.pelime.ebanck.server.support.EbanckHttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class EbankMessageController {

    @Autowired
    MessageLinkService messageLinkService;

    @RequestMapping(value = "/saveLink",produces = "application/json;charset=UTF-8")
    public Object saveLink(String phones,String body){
        MessageLink messageLink= messageLinkService.saveMessageLink(phones,body);

        return new EbanckHttpEntity(EbanckCode.SUCCESS,messageLink.getFlag());
    }

    @RequestMapping(value = "/getLink",produces = "application/json;charset=UTF-8")
    public Object getLink(){
        MessageLink messageLink=messageLinkService.getLink();
        return new EbanckHttpEntity(EbanckCode.SUCCESS,messageLink);
    }

}
