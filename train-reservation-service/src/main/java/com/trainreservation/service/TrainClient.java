package com.trainreservation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trainreservation.DTO.Train;

//@FeignClient(url="http://localhost:8085/api/trains",value="Train-Client")
@FeignClient(name = "TRAIN-SERVICE",path="/api/trains")
public interface TrainClient {

        @GetMapping("/all-Train")
		List<Train> fetchAllTrain();
		
		@GetMapping("/findTrain/{id}")
		Train fetchTrain(@PathVariable Long id);
	

}
