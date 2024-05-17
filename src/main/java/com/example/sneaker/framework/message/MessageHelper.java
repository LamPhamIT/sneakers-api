package com.example.sneaker.framework.message;

import com.example.sneaker.framework.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class MessageHelper implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(MessageHelper.class);

    private static MessageHelper instance = null;

    private final MessageSource messageSource;

    public MessageHelper(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public static Message getMessage(Message.Keys key, Object... agruments){
          Message message = new Message();
          message.setKey(key);
          try{
              List<String> strings = new ArrayList<>();
              for(Object arg : agruments){
                  if(arg != null) strings.add(arg.toString());
              }

              Locale locale = LocaleContextHolder.getLocale();
              if(Objects.isNull(locale)) locale = Locale.getDefault();
              message.setContent(instance.messageSource.getMessage(key.name(), strings.toArray(), locale));
          } catch (Exception ex){

          } finally {
              return message;
          }
    }

    public static String getMessageWithCode(String messageCode) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            if (Objects.isNull(locale)) {
                locale = Locale.getDefault();
            }
            return instance.messageSource.getMessage(messageCode, null, locale);
        } catch (Exception ex) {

        } finally {
            return messageCode;
        }
    }


    public static Message getMessage(String message) {
        Message messageContent = new Message();
        messageContent.setContent(message);
        return messageContent;
    }

    public static MessageHelper getInstance(){
        return instance;
    }

    @Override
    public void afterPropertiesSet() throws  Exception {
        if(instance == null){
            instance = this;
        }
    }
}
