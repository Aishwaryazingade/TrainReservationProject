package com.trainreservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainreservation.Dto.Complaint;
import com.trainreservation.Dto.Reservation;
import com.trainreservation.entity.Train;
import com.trainreservation.service.TrainService;

@RestController
@RequestMapping("/api/trains")

public class TrainController {
	private final TrainService trainService;

	public TrainController(TrainService trainService) {
		this.trainService=trainService;
	}

	@PostMapping("/addtrain")
	public ResponseEntity<Train> addTrain(@RequestBody Train train){
		return new ResponseEntity<>(trainService.addTrain(train),HttpStatus.CREATED);
	}
	
	@GetMapping("/all-Train")
	public List<Train> fetchAllTrain() {
		return trainService.fetchAllTrain();
	}

	@PutMapping("/update-Train/{id}")
	public Train updateTrain(@PathVariable Long id, @RequestBody Train Train) {
		return trainService.updateTrain(id, Train);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTrain(@PathVariable Long id) {
		trainService.deleteTrain(id);
		return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);

	}

	@GetMapping("/findTrain/{id}")
	public Train findByTrainId(@PathVariable Long id) {
        return trainService.findTrainById(id);
	}
	
	@GetMapping("/getAllReservation") 
	public ResponseEntity<List<Reservation>> getAllReservation(){
		return new ResponseEntity<>(trainService.getReservations(), HttpStatus.OK);
	}
	
	@GetMapping("/get-all-complaints") 
	public ResponseEntity<List<Complaint>> getAllComplaints(){
		return new ResponseEntity<>(trainService.getAllComplaints(), HttpStatus.OK);
	}

}
