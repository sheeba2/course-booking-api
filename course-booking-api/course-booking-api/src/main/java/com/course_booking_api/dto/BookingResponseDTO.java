package com.course_booking_api.dto;

import lombok.Data;

@Data
public class BookingResponseDTO {

    private Long bookingId;
    private Long offeringId;
    private String courseName;
}