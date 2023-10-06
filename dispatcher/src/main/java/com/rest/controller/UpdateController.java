package com.rest.controller;


import com.rest.service.UpdateProducer;
import com.rest.utils.MessageUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.rest.RabbitQueue.*;

@Log4j
@Component
 public class UpdateController {


   private TelegramBot telegramBot;
   private final MessageUtils messageUtils;
   private final UpdateProducer updateProducer;

   public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer) {
      this.messageUtils = messageUtils;
      this.updateProducer = updateProducer;
   }


   public void registerBot(TelegramBot telegramBot){
      this.telegramBot = telegramBot;
   }



   public void processUpdate(Update update){
      if(update == null){
         log.error("Received update is null");
         return;
      }
      if(update.getMessage() != null){
         distributeMessagesByType(update);
      }else{
         log.error("Unsupported message type is received: " + update);
      }
   }

   private void distributeMessagesByType(Update update) {
      var message = update.getMessage();
      
      if(message.getText() != null){
         processTextMessages(update);
      }else if(message.getDocument() != null){
         processDocumentMessage(update);
      }else if(message.getPhoto() != null){
         processPhotoMessage(update);
      }else{
         setUnsupportedMessageTypeView(update);
      }
   }

   private void setUnsupportedMessageTypeView(Update update) {
      var sendMessage = messageUtils.generateSendMessageWithText(update,"Unsupported type of messages");
      setView(sendMessage);

   }
   private void setView(SendMessage sendMessage){
      telegramBot.sendAnswerMessage(sendMessage);
   }

   private void setFiIeIsReceivedView(Update update) {
      var sendMessage = messageUtils.generateSendMessageWithText(update,"Process is loading......");
      setView(sendMessage);
   }

   //processes
   private void processPhotoMessage(Update update) {
      updateProducer.producer(PHOTO_MESSAGE_UPDATE, update);
      setFiIeIsReceivedView(update);
   }


   private void processDocumentMessage(Update update) {
      updateProducer.producer(DOC_MESSAGE_UPDATE, update);
      setFiIeIsReceivedView(update);
   }

   private void processTextMessages(Update update) {
      updateProducer.producer(TEXT_MESSAGE_UPDATE, update);
      setFiIeIsReceivedView(update);
   }
}
