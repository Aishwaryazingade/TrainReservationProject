package com.trainreservation.complaint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainreservation.complaint.entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

}
