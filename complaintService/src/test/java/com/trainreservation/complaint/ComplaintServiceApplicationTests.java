package com.trainreservation.complaint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.trainreservation.complaint.dto.ComplaintDto;
import com.trainreservation.complaint.entity.Complaint;
import com.trainreservation.complaint.entity.Train;
import com.trainreservation.complaint.repository.ComplaintRepository;
import com.trainreservation.complaint.service.ComplaintService;
import com.trainreservation.complaint.service.TrainClient;

import lombok.AllArgsConstructor;

@SpringBootTest
@AllArgsConstructor
class ComplaintServiceApplicationTests {

	ComplaintRepository complaintRepository;
	TrainClient trainClient;
	ComplaintService complaintService;
	@Test
	public void testCreateComplaint() {
        
        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setTrainNo("12345");
        complaintDto.setComplaintStatement("Complaint statement");

        Long userId = 1L;

        
        Train train1 = new Train(1L,"787878","Pune","Mumbai",160,"12:00",800,LocalDate.of(2025, 2, 1));
        
        Train train2 = new Train(2L, "67890", "Mumbai", "Pune", 150, "15:00", 700, LocalDate.of(2025, 2, 2));
        List<Train> trainList = Arrays.asList(train1, train2);

        when(trainClient.getTrainList()).thenReturn(trainList);

        Complaint complaint = new Complaint();
        complaint.setUserId(userId);
        complaint.setComplaintStatement(complaintDto.getComplaintStatement());
        complaint.setTrainNo(complaintDto.getTrainNo());

        when(complaintRepository.save(any(Complaint.class))).thenReturn(complaint);

        // Act
        Complaint result = complaintService.createComplaint(complaintDto, userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(complaintDto.getComplaintStatement(), result.getComplaintStatement());
        assertEquals(complaintDto.getTrainNo(), result.getTrainNo());
    }

}
