package com.course_booking_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionRequestDTO {

    private Long offeringId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
