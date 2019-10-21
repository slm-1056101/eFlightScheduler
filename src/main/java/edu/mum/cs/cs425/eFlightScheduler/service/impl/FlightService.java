package edu.mum.cs.cs425.eFlightScheduler.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.eFlightScheduler.models.Flight;
import edu.mum.cs.cs425.eFlightScheduler.repository.IFlightRepository;
import edu.mum.cs.cs425.eFlightScheduler.service.IFlightService;

@Service
public class FlightService implements IFlightService{
	
	@Autowired
	private IFlightRepository flightRepository;

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll(Sort.by("id"));
	}

	@Override
	public Flight addNewFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Optional<Flight> findById(Long id) {
		return flightRepository.findById(id);
	}
}
