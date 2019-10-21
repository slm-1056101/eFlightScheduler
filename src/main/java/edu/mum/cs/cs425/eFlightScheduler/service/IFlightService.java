package edu.mum.cs.cs425.eFlightScheduler.service;

import java.util.List;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;

public interface IFlightService {
	
	List<Flight> getAllFlights();
	Flight addNewFlight(Flight flight);

}
