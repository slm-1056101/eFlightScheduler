package edu.mum.cs.cs425.eFlightScheduler.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;


public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {

    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.json}")
  public void receive(Flight flight) {
    LOGGER.info("received flight='{}'", flight.toString());
    latch.countDown();
  }
}
