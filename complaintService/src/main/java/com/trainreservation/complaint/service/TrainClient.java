package com.trainreservation.complaint.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.trainreservation.complaint.entity.Train;

@FeignClient(name="TRAIN-SERVICE")
public interface TrainClient {

	@GetMapping("/api/trains/all-Train")
	public List<Train> getTrainList();
}
