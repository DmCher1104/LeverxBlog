package com.leverx.service;

import javax.mail.MessagingException;

public interface MailService {

    public void sendMessage(String to, String subject, String text);

}
