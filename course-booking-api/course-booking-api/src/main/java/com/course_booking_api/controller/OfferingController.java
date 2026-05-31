package com.course_booking_api.controller;

import com.course_booking_api.dto.OfferingResponseDTO;
import com.course_booking_api.entity.Offering;
import com.course_booking_api.repository.OfferingRepository;
import com.course_booking_api.utility.MapperUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offerings")
public class OfferingController {

    private final OfferingRepository offeringRepository;

    public OfferingController(OfferingRepository offeringRepository) {
        this.offeringRepository = offeringRepository;
    }

    // Get all offerings (Parent view)
    @GetMapping
    public List<OfferingResponseDTO> getAllOfferings(
            @RequestParam(defaultValue ="Asia/Kolkata" ) String timezone) {

        return offeringRepository.findAll()
                .stream()
                .map(offering -> MapperUtil.toOfferingDTO(offering, timezone))
                .toList();
    }
}
