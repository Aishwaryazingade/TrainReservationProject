package com.trainreservation.DTO;

import com.trainreservation.entity.Reservation;

import lombok.Data;
@Data
public class ReservationDTO {
	private Train train;
	private Reservation reservation;
	
	public ReservationDTO(Train train,Reservation reservation) {
		this.train=train;
		this.reservation=reservation;
	}
}
