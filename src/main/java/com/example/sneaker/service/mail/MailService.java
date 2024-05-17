package com.example.sneaker.service.mail;

import com.example.sneaker.model.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;

@Service
public class MailService implements IMailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private final String USER = "user";

    private final String BASE_URL = "baseUrl";

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final MessageSource messageSource;

    public MailService(
            JavaMailSender javaMailSender,
            SpringTemplateEngine templateEngine,
            MessageSource messageSource
    ) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isHtml) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
        }
    }

    @Override
    public void sendEmailFromTemplate(User user, String template, String titleKey) {
        if (user.getEmail() == null) return;
        Context context = new Context();
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, "http://localhost:5173");
        String content = templateEngine.process(template, context);
        String subject = messageSource.getMessage(titleKey, null, new Locale("en"));
        sendEmail(user.getEmail(), subject, content, true);
    }

    @Override
    public void sendPasswordResetEmail(User user) {
        sendEmailFromTemplate(user, "email/passwordResetMail", "email.reset.title");
    }
}
