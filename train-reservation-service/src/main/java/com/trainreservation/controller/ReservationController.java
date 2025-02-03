package com.trainreservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainreservation.DTO.ReservationDTO;
import com.trainreservation.DTO.Train;
import com.trainreservation.entity.Reservation;
import com.trainreservation.service.PassengerService;
import com.trainreservation.service.TrainReservationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

	private final TrainReservationService reservationService;

	private final PassengerService passengerService;
	

	//making reservation by user
	@PostMapping
	public ResponseEntity<ReservationDTO> createReservation(@RequestBody @Valid Reservation reservation) {
		 return new ResponseEntity<ReservationDTO>(reservationService.createReservation(reservation), HttpStatus.CREATED);
	}

	//get reservation details using userid
	@GetMapping("/user/{userId}")
	public List<Reservation> getReservationsByUser(@PathVariable Long userId) {
		return reservationService.getReservationsByUser(userId);
	}

	//Delete reservation
	@DeleteMapping("/{reservationId}")
	public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long reservationId) {
		String msg=reservationService.deleteReservation(reservationId);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	//
	@GetMapping("/{routeFrom}/{routeTo}")
	public ResponseEntity<List<Train>> findBusByFromAndToDestination(@PathVariable String routeFrom,@PathVariable String routeTo) {
		return new ResponseEntity(reservationService.findTrainByFromAndToDestination(routeFrom, routeTo),HttpStatus.OK);

	}
	  @DeleteMapping("/deletePassenger/{reservationId}/{passengerId}")
	    public ResponseEntity<String> deletePassengerById(@PathVariable Long reservationId,@PathVariable Long passengerId ){
	    	return new ResponseEntity<>(passengerService.deletePassengerFromReservation(reservationId, passengerId), HttpStatus.OK);
	    }
	    
	    @GetMapping("/getAllReservation")
	    public ResponseEntity<List<Reservation>> getAllReservation(){
	    	return new ResponseEntity(reservationService.getAllReservation(),HttpStatus.OK);
	    }
	    
	    @GetMapping("/getReservation/{reservationId}")
		public ResponseEntity<Reservation> getReservation(@PathVariable("reservationId") Long reservationId) {
			return new ResponseEntity<Reservation>(reservationService.getReservation(reservationId),HttpStatus.OK);
		}
	    

}
