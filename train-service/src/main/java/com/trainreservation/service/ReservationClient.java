package com.trainreservation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.trainreservation.Dto.Reservation;

//@FeignClient(url="http://localhost:8083/api/reservations",value="bus-booking")
@FeignClient(name="TRAIN-RESERVATION-SERVICE")
public interface ReservationClient {
	
	@GetMapping("/api/reservations/getAllReservation")
	List<Reservation> getAllReservation();

}
