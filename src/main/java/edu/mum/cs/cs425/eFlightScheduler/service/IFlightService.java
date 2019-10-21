package edu.mum.cs.cs425.eFlightScheduler.service;

import java.util.List;
import java.util.Optional;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;

public interface IFlightService {

    List<Flight> getAllFlights();

    Flight addNewFlight(Flight flight);

    Optional<Flight> findById(Long id);

    void deleteFlight(Long flightId);

}
