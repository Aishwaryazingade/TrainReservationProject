package com.trainreservation.Dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Complaint {
     Long complaintId;
     Long userId;
     String trainNo;
     String complaintStatement;
}
