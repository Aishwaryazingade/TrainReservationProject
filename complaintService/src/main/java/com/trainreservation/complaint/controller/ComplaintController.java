package com.trainreservation.complaint.controller;

import java.util.List;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainreservation.complaint.dto.ComplaintDto;
import com.trainreservation.complaint.entity.Complaint;
import com.trainreservation.complaint.service.ComplaintService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/complaints")
@EnableFeignClients
public class ComplaintController {

	private ComplaintService complaintService;


	@PostMapping("/add-complaint/{userId}")
	public Complaint createComplaint(@RequestBody ComplaintDto complaintDto,@PathVariable("userId") Long userId){
		return complaintService.createComplaint(complaintDto,userId);
	}
	
	@GetMapping("/get-all-complaints")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

	


}
