package com.course_booking_api.dto;


import lombok.Data;

import java.util.List;

@Data
public class OfferingResponseDTO {

    private Long id;
    private String courseName;
    private String teacherName;
    private String timezone;
    private List<SessionResponseDTO> sessions;
}
