package com.trainreservation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.trainreservation.Dto.Complaint;

//@FeignClient(url="http://localhost:8090/complaints",value="complaints")
@FeignClient(name="COMPLAINTSERVICE")
public interface ComplaintClient {
	
	@GetMapping("/complaints/get-all-complaints")
	List<Complaint> getAllComplaints();
}






