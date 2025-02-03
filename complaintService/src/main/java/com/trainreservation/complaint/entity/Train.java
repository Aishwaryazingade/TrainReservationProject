package com.trainreservation.complaint.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    private Long trainId;

 
    private String trainNo;


    private String routeFrom;

 
    private String routeTo;


    private int seats;

    private String departureTime;

   
    private int price;
	

	private LocalDate scheduledDate;




}
