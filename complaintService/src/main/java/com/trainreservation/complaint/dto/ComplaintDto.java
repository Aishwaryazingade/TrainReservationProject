package com.trainreservation.complaint.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ComplaintDto {
     
     Long userId;
     String trainNo;
     String complaintStatement;
}
