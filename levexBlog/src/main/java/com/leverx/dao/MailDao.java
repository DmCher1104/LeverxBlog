package com.leverx.dao;

public interface MailDao {
    public void sendMessage(String to, String subject, String text);
}
