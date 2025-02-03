package com.trainreservation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trainreservation.entity.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {

}
