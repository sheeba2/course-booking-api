package com.course_booking_api.controller;

import com.course_booking_api.dto.BookingRequestDTO;
import com.course_booking_api.dto.BookingResponseDTO;
import com.course_booking_api.service.BookingService;
import com.course_booking_api.utility.MapperUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }



    @GetMapping("/{parentId}")
    public List<BookingResponseDTO> getBookings(@PathVariable Long parentId) {

        return bookingService.getBookingsByParent(parentId)
                .stream()
                .map(MapperUtil::toBookingDTO)
                .toList();
    }

    // Book an offering
     @PostMapping
    public BookingResponseDTO createBooking(
            @Valid @RequestBody BookingRequestDTO request) {

        return MapperUtil.toBookingDTO(
                bookingService.bookOffering(request.getParentId(), request.getOfferingId())
        );
    }
}
