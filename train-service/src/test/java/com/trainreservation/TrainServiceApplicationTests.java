package com.trainreservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.trainreservation.entity.Train;
import com.trainreservation.repository.TrainRepo;
import com.trainreservation.service.TrainService;

@SpringBootTest
class TrainServiceApplicationTests {

	
	@Mock
    private TrainRepo trainRepo;

    @InjectMocks
    private TrainService trainService;
    
    Train train;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Train train = new Train();
        train.setTrainNo("pnr3456");
        train.setRouteFrom("hubli");
        train.setRouteTo("bangalore");
        train.setSeats(50);
        train.setDepartureTime("06:40");
        train.setPrice(150);     
    }
	
	@Test
    public void testFetchAllTrainPositive() {

//		Train train1 = new Train(1L, "12345", "Hyderabad", "Bangalore", 100, "10:00", 500, LocalDate.now().plusDays(1));
//		Train train2 = new Train(2L, "67890", "Mumbai", "Pune", 150, "15:00", 700, LocalDate.now().plusDays(2));
//		List<Train> trains = Arrays.asList(train1, train2);
//
//		when(trainRepo.findAll()).thenReturn(trains);
//
//		// Act
//		List<Train> result = trainService.fetchAllTrain();
//
//		// Assert
//		assertNotNull(result);
//		assertEquals(2, result.size());

		LocalDate scheduledDate1 = LocalDate.of(2025, 2, 1);
        LocalDate scheduledDate2 = LocalDate.of(2025, 2, 2);

        Train train1 = new Train(1L, "12345", "Hyderabad", "Bangalore", 100, "10:00", 500, scheduledDate1);
        Train train2 = new Train(2L, "67890", "Mumbai", "Pune", 150, "15:00", 700, scheduledDate2);
        List<Train> trains = Arrays.asList(train1, train2);

        when(trainRepo.findAll()).thenReturn(trains);

        // Act
        List<Train> result = trainService.fetchAllTrain();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(train1));
        assertTrue(result.contains(train2));

        // Additional meaningful assert
        Train fetchedTrain1 = result.get(0);
        assertEquals("12345", fetchedTrain1.getTrainNo());
        assertEquals("Hyderabad", fetchedTrain1.getRouteFrom());
        assertEquals("Bangalore", fetchedTrain1.getRouteTo());
        assertEquals(100, fetchedTrain1.getSeats());
        assertEquals("10:00", fetchedTrain1.getDepartureTime());
        assertEquals(500, fetchedTrain1.getPrice());
        assertEquals(scheduledDate1, fetchedTrain1.getScheduledDate());
    }
	
//	@Test
//    public void testFetchAllTrainError() {
//        // Arrange
//		Train train1 = new Train(1L, "pnr3498", "Delhi", "Agra", 80, "09:20", 350);
//		Train train2 = new Train(5L, "cnr4567", "bhuvaneshwara", "bhopal", 70, "11:40", 650);
//		
//        
//		Train train3 = new Train(7L, "pnr3498", "Delhi", "Agra", 80, "09:20", 350); 
//       
//		List<Train> expectedTrains1 = Arrays.asList(train3);
//        List<Train> expectedTrains=Arrays.asList(train1, train2);
//        
//        when(trainRepo.findAll()).thenReturn(expectedTrains1);
//
//        // Act
//        List<Train> actualTrains = trainService.fetchAllTrain();
//        System.out.println(expectedTrains);
//        System.out.println(actualTrains);
//
//        // Assert
//        assertEquals(expectedTrains, actualTrains);
//        verify(trainRepo, times(1)).findAll();
//    }
//	
//	 @Test
	 public void testFetchAllTrainEmpty() {
	        // Arrange
			
			List<Train> expectedTrains = Collections.emptyList();
	        when(trainRepo.findAll()).thenReturn(expectedTrains);

	        // Act
	        List<Train> actualTrains = trainService.fetchAllTrain();
	        System.out.println(actualTrains+"actual");

	        // Assert
	        assertEquals(expectedTrains, actualTrains);
	        verify(trainRepo, times(1)).findAll();
	    }
	
//		@Test
		public void testAddTrain() {
			Train train = new Train();
	        train.setTrainNo("pnr3456");
	        train.setRouteFrom("hubli");
	        train.setRouteTo("bangalore");
	        train.setSeats(50);
	        train.setDepartureTime("06:40");
	        train.setPrice(150);

	        when(trainRepo.save(train)).thenReturn(train);

	        Train result = trainService.addTrain(train);

	        assertEquals(train, result);
			
		}
		
//		@Test
	    public void testAddTrainFailure() {
	       

	        // Simulate a failure in saving the train
	        when(trainRepo.save(train)).thenReturn(null);

	        Train result = trainService.addTrain(train);

	        assertNull(result);
	        System.out.print("false hereeeeeeeeeeee");

		}
		
//		@Test
	    public void testAddTrainErrorHandling() {
         // Simulate an exception being thrown when saving the train
	        doThrow(new RuntimeException("Database error")).when(trainRepo).save(train);

	       
	        // Assert that the exception is thrown when addTrain is called
	        assertThrows(RuntimeException.class, () -> {
	            trainService.addTrain(train);
	        });
	    }

}
