package com.trainreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainreservation.entity.Train;

@Repository
public interface TrainRepo extends JpaRepository<Train, Long>{
	

	boolean existsByTrainNo(String trainNo);
}
