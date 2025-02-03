package com.trainreservation.complaint.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.trainreservation.DTO.ReservationDTO;
//import com.trainreservation.DTO.Train;
import com.trainreservation.complaint.dto.ComplaintDto;
import com.trainreservation.complaint.entity.Complaint;
import com.trainreservation.complaint.entity.Train;
import com.trainreservation.complaint.exception.ResourceNotFoundException;
import com.trainreservation.complaint.repository.ComplaintRepository;
//import com.trainreservation.entity.Reservation;
//import com.trainreservation.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;


@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
	ComplaintRepository complaintRepository;
	
    @Autowired
	TrainClient trainClient;
	@Override
	public Complaint createComplaint(ComplaintDto complaintDto, Long userId) {
		    
		 List<Train> trainList = trainClient.getTrainList();
		    
		    // Check if the train exists in the trainList
		    boolean trainExists = false;
		    for (Train train : trainList) {
		        if (complaintDto.getTrainNo().equalsIgnoreCase(train.getTrainNo())) {
		            trainExists = true;
		            break;
		        }
		    }
		    
		    if (!trainExists) {
		        throw new ResourceNotFoundException("Train not found");
		    }
		    
		    // Create and save the complaint if the train exists
		    Complaint complaint = new Complaint();
		    complaint.setUserId(userId);
		    complaint.setComplaintStatement(complaintDto.getComplaintStatement());
		    complaint.setTrainNo(complaintDto.getTrainNo());
		    return complaintRepository.save(complaint);
		}
	        
	
	
	 public List<Complaint> getAllComplaints() {
		 System.out.println("complaints"+complaintRepository.findAll());
	        return complaintRepository.findAll();
	  }
	
}
