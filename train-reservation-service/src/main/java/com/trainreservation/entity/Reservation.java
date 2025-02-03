package com.trainreservation.entity;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Reservation {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull
	    private Long userId; // Reference to the user making the reservation

	    @NotNull
	    private Long trainId; // Reference to the bus

	    @NotNull
//	    @FutureOrPresent
	    private LocalDate date;

	    @NotNull
	    private Integer numberOfSeats;

	    private Double totalAmount;
	
	    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	    @JoinColumn(name = "reservation_id")	
	    private List<Passenger> passengers;
	
}
