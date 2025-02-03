package com.trainreservation.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trainreservation.entity.Passenger;
import com.trainreservation.entity.Reservation;
import com.trainreservation.exception.ResourceNotFoundException;
import com.trainreservation.repository.PassengerRepo;
import com.trainreservation.repository.ReservationRepo;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {
	
	private PassengerRepo passengerRepo;
	private ReservationRepo reservationRepo;
	private TrainClient trainClient;
	
	@Override
	public Passenger addPassenger(Passenger passenger) {
		
		return passengerRepo.save(passenger);
	}

	@Override
	public List<Passenger> findAllPassenger() {
		
		return passengerRepo.findAll();
	}

	@Override
	public String deletePassenger(Long id)  {
		Optional<Passenger> passenger= passengerRepo.findById(id);
		
		if(!passenger.isPresent()) {
			throw new ResourceNotFoundException("passenger Id not found");
		}
		
		passengerRepo.deleteById(id);
		return "deleted passenger";
	}

	@Override
	public Passenger updatePassenger(Passenger passenger) {
		Optional<Passenger> passenger1=passengerRepo.findById(passenger.getPid());
		if(!passenger1.isPresent()) {
			throw new ResourceNotFoundException("passenger Id not found");
		}
		
		return passengerRepo.save(passenger);
	}

	@Override
	public Passenger findById(Long id) throws ResourceNotFoundException {
		Optional<Passenger> passenger=passengerRepo.findById(id);
		if(!passenger.isPresent()) {
			throw new ResourceNotFoundException("passenger Id not found");
		}
		
		return passenger.get();
	}
	
	
	public String deletePassengerFromReservation(Long reservationId, Long passengerId) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(reservationId);

        Optional<Passenger> passengerOpt = passengerRepo.findById(passengerId);
        
        if (!reservationOpt.isPresent()) {
        	throw new ResourceNotFoundException("Reservation Not Found");
        	}
         if( !passengerOpt.isPresent()) {
        	throw new ResourceNotFoundException("Passenger not found");
        	}
        
        else {
            
        	Reservation reservation = reservationOpt.get();
            reservation.getPassengers().removeIf(passenger -> passenger.getPid().equals(passengerId));
            reservation.setNumberOfSeats(reservation.getNumberOfSeats()-1);
            reservation.setTotalAmount(reservation.getTotalAmount()-trainClient.fetchTrain(reservation.getTrainId()).getPrice());
            reservationRepo.save(reservation);
            return "Passenger deleted in Reservation";
        }
    }
}
