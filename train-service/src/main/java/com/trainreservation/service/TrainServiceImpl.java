package com.trainreservation.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.trainreservation.Dto.Complaint;
import com.trainreservation.Dto.Reservation;
import com.trainreservation.entity.Train;
import com.trainreservation.exception.ResourceNotFoundException;
import com.trainreservation.repository.TrainRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainServiceImpl implements TrainService {

	TrainRepo trainRepo;
	ReservationClient reservationClient;
    ComplaintClient complaintClient;


	public List<Train> fetchAllTrain() {
		return trainRepo.findAll();
	}

	public Train updateTrain(Long id, Train train) {
		Train existingTrain = trainRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found"));

		existingTrain.setTrainNo(train.getTrainNo());
		existingTrain.setSeats(train.getSeats());
		existingTrain.setScheduledDate(train.getScheduledDate());
		existingTrain.setRouteTo(train.getRouteTo());
		existingTrain.setRouteFrom(train.getRouteFrom());
		existingTrain.setPrice(train.getPrice());
		existingTrain.setDepartureTime(train.getDepartureTime());
		
		// Save the updated Train
		return trainRepo.save(existingTrain);
	}

	public void deleteTrain(Long id) {
		Train existingTrain = trainRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found"));
        trainRepo.deleteById(id);
	}

	public Train findTrainById(Long id) {
		return trainRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found"));
	}

	public Train addTrain(Train train) {
		if (trainRepo.existsByTrainNo(train.getTrainNo())) {
            throw new ResourceNotFoundException("Train with this number already exists");
        }
	   return trainRepo.save(train);
	}

	@Override
	public List<Reservation> getReservations() {
		return reservationClient.getAllReservation();
	}

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintClient.getAllComplaints();
	}

}
