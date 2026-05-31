package com.course_booking_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionResponseDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
