package com.course_booking_api.controller;

import com.course_booking_api.dto.BookingRequestDTO;
import com.course_booking_api.dto.BookingResponseDTO;
import com.course_booking_api.dto.OfferingResponseDTO;
import com.course_booking_api.service.BookingService;
import com.course_booking_api.service.OfferingService;
import com.course_booking_api.utility.MapperUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {

    private final BookingService bookingService;
    private final OfferingService offeringService;

    public ParentController(BookingService bookingService, OfferingService offeringService) {
        this.bookingService = bookingService;
        this.offeringService = offeringService;
    }

//    @PostMapping("/parents/{parentId}/bookings")
//    public BookingResponseDTO book(@RequestBody BookingRequestDTO request) {
//        return MapperUtil.toBookingDTO(
//                bookingService.bookOffering(request.getParentId(), request.getOfferingId())
//        );
//    }

    @GetMapping("/offerings")
    public List<OfferingResponseDTO> getAvailableOfferings(
            @RequestParam String timezone) {

        return offeringService.getAllOfferings()
                .stream()
                .map(offering -> MapperUtil.toOfferingDTO(offering, timezone))
                .toList();
    }
    @GetMapping("/bookings/{parentId}")
    public List<BookingResponseDTO> getBookings(@PathVariable Long parentId) {
        return bookingService.getBookingsByParent(parentId)
                .stream()
                .map(MapperUtil::toBookingDTO)
                .toList();
    }
}
