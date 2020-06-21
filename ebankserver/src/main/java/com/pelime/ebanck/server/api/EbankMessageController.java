package com.pelime.ebanck.server.api;

import com.pelime.ebanck.server.domain.MessageLink;
import com.pelime.ebanck.server.service.MessageLinkService;
import com.pelime.ebanck.server.support.EbanckCode;
import com.pelime.ebanck.server.support.EbanckHttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/phonemsg")
public class EbankMessageController {

    @Autowired
    MessageLinkService messageLinkService;


    @RequestMapping(value = "/savelink",produces = "application/json;charset=UTF-8")
    public Object saveLink(@RequestBody Map<String,Object> data){
        String phones=data.get("phones").toString();
        String body=data.get("body").toString();
        MessageLink messageLink= messageLinkService.saveMessageLink(phones,body);
        return new EbanckHttpEntity(EbanckCode.SUCCESS,messageLink.getFlag());
    }

    @RequestMapping(value = "/getlink",produces = "application/json;charset=UTF-8")
    public Object getLink(){
        MessageLink messageLink=messageLinkService.getLink();
        return new EbanckHttpEntity(EbanckCode.SUCCESS,messageLink);
    }

    @GetMapping(value = "/messagemodel", produces = "application/json;charset=UTF-8")
    public Object getDisplayMessageModel(){
        return new EbanckHttpEntity(EbanckCode.SUCCESS,messageLinkService.getDisplayMessageModel());
    }

}
