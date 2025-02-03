package com.trainreservation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trainreservation.DTO.ReservationDTO;
import com.trainreservation.DTO.Train;
import com.trainreservation.entity.Reservation;
import com.trainreservation.exception.ResourceNotFoundException;
import com.trainreservation.repository.ReservationRepo;

import feign.FeignException;

@Service
public class TrainReservationService {

	private ReservationRepo reservationRepository;

	private TrainClient trainClient;

	public TrainReservationService(ReservationRepo reservationRepository, TrainClient trainClient) {
		super();
		this.reservationRepository = reservationRepository;
		this.trainClient = trainClient;
	}

	public ReservationDTO createReservation(Reservation reservation) {

		try {
			Train trainDetails = trainClient.fetchTrain(reservation.getTrainId());

			if (trainDetails.getSeats() <= 0) {
				throw new ResourceNotFoundException("No Seats left");
			} else {

				// Calculates the total price dynamically
				double totalAmount = reservation.getNumberOfSeats() * trainDetails.getPrice();
				reservation.setTotalAmount(totalAmount);

				// Save the reservation
				LocalDate currentDate = LocalDate.now();

				if (reservation.getDate().isAfter(trainDetails.getScheduledDate())) {
					throw new ResourceNotFoundException(
							"You can't make  reservation for this date ,train was scheduled on "
									+ trainDetails.getScheduledDate());
				}
				if (reservation.getDate().isBefore(currentDate)) {
					throw new ResourceNotFoundException(
							"You can't make  reservation for past date " + reservation.getDate());
				}
				Reservation res = reservationRepository.save(reservation);

				return new ReservationDTO(trainDetails, res);
			}
		} catch (FeignException e) {
			if (e.status() == 404) {
				throw new ResourceNotFoundException("Train not found with ID: " + reservation.getTrainId());
			}
			// Handle other exceptions
			throw new RuntimeException("An error occurred while fetching train details", e);

		}

	}

	public List<Reservation> getReservationsByUser(Long userId) {
		 List<Reservation> reservations = reservationRepository.findByUserId(userId);
	      reservations.removeIf(reservation -> reservation.getNumberOfSeats() == 0);
	      return reservations;
	}

	
	public String deleteReservation(Long id) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

		LocalDate reservationDate = reservation.getDate();
		LocalDate currentDate = LocalDate.now();
	

		Train trainDetails = trainClient.fetchTrain(reservation.getTrainId());
		System.out.println(trainDetails.getScheduledDate());

		if ((currentDate.isAfter(trainDetails.getScheduledDate()))
				|| (currentDate.isAfter(trainDetails.getScheduledDate().minusDays(7)))) {
			throw new ResourceNotFoundException(
					"Reservation cannot be cancelled before 7 days of the train's scheduled date.");
		} else {
			reservationRepository.deleteById(id);
		}
		return "Reservation deleted";
	}
	

	public List<Train> findTrainByFromAndToDestination(String routeFrom, String routeTo) {
		List<Train> trains = trainClient.fetchAllTrain();

		List<Train> requiredTrains = new ArrayList<>();

		for (Train train : trains) {
			if (routeFrom.equalsIgnoreCase(train.getRouteFrom()) && routeTo.equalsIgnoreCase(train.getRouteTo())) {
				requiredTrains.add(train);
			}

		}
		 if (requiredTrains.isEmpty()) {
		        throw new ResourceNotFoundException("No trains found for this location");
		    }
		return requiredTrains;
	}

	
	public List<Reservation> getAllReservation() {
		
	        List<Reservation> reservations = reservationRepository.findAll();
	        reservations.removeIf(reservation -> reservation.getNumberOfSeats() == 0);
	        return reservations;

	}
	

	public Reservation getReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID"));
	}

}
