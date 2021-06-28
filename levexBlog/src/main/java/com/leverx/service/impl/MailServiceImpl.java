package com.leverx.service.impl;

import com.leverx.dao.MailDao;
import com.leverx.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailDao mailDao;

    @Override
    public void sendMessage(String to, String subject, String text) {
       mailDao.sendMessage(to,subject,text);
    }


}
