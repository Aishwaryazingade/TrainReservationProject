package com.trainreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainreservation.Dto.Complaint;
import com.trainreservation.Dto.Reservation;
import com.trainreservation.entity.Train;
import com.trainreservation.exception.ResourceNotFoundException;


public interface TrainService {
	
	

	Train addTrain(Train train);

	List<Train> fetchAllTrain();

	Train updateTrain(Long id, Train train) throws ResourceNotFoundException;

	void deleteTrain(Long id) throws ResourceNotFoundException;

	Train findTrainById(Long id) throws ResourceNotFoundException;
	
	List<Reservation> getReservations();
	
	List<Complaint> getAllComplaints();

}