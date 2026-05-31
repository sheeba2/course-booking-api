package com.course_booking_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Offering ID is required")
    private Long offeringId;

    @NotNull(message = "Parent ID is required")
    private Long parentId;
}