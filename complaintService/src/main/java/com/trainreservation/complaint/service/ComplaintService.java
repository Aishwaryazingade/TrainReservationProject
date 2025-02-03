package com.trainreservation.complaint.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainreservation.complaint.dto.ComplaintDto;
import com.trainreservation.complaint.entity.Complaint;

import lombok.AllArgsConstructor;


public interface ComplaintService {

   

	public Complaint createComplaint(ComplaintDto complaintDto, Long userId);

	public List<Complaint> getAllComplaints();
	
}
