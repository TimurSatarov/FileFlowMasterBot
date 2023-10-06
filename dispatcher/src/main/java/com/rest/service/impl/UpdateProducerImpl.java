package com.rest.service.impl;

import com.rest.service.UpdateProducer;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@Log4j
public class UpdateProducerImpl implements UpdateProducer{
   @Override
   public void producer(String rabbitQueue, Update update) {
      log.debug(update.getMessage().getText());
   }
}
