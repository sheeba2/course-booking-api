package com.course_booking_api.dto;

import lombok.Data;

@Data
public class CreateOfferingRequest {

    private Long courseId;
    private Long teacherId;
    private String timezone;
}