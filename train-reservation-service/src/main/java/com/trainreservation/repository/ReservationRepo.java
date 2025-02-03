package com.trainreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainreservation.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{

	public List<Reservation> findByUserId(Long userId);

}
