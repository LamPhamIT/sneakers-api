package com.example.sneaker.service.mail;

import com.example.sneaker.model.entity.User;

public interface IMailService {

    void sendEmail(String to, String subject, String content, boolean isHtml);
    
    void sendEmailFromTemplate(User user, String template, String titleKey);

    void sendPasswordResetEmail(User user);
}
