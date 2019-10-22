package edu.mum.cs.cs425.eFlightScheduler.kafka.producer;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;


public class Sender {

  private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

  @Value("${kafka.topic.json}")
  private String jsonTopic;

  @Autowired
  private KafkaTemplate<String, Flight> kafkaTemplate;

  public void send(Flight flight) {
    LOGGER.info("sending flight='{}'", flight.toString());
    kafkaTemplate.send(jsonTopic, flight);
  }
}
