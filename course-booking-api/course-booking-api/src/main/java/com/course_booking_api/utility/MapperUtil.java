package com.course_booking_api.utility;


import com.course_booking_api.dto.*;
import com.course_booking_api.entity.*;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    public static OfferingResponseDTO toOfferingDTO(Offering offering, String parentTimeZone) {

        OfferingResponseDTO dto = new OfferingResponseDTO();
        dto.setId(offering.getId());
        dto.setCourseName(offering.getCourse().getName());
        dto.setTeacherName(offering.getTeacher().getName());
        dto.setTimezone(offering.getTimezone());

        List<SessionResponseDTO> sessions = offering.getSessions()
                .stream()
                .map(session -> MapperUtil.toSessionDTO(session, parentTimeZone))
                .toList();

        dto.setSessions(sessions);

        return dto;
    }

    public static SessionResponseDTO toSessionDTO(Session session, String parentTimeZone) {

        ZoneId zoneId;
        try {
            zoneId = ZoneId.of(parentTimeZone);
        }catch(Exception ex){
            zoneId =ZoneId.of("Asia/Kolkata");

        }

        SessionResponseDTO dto = new SessionResponseDTO();

        dto.setId(session.getId());

        dto.setStartTime(
                session.getStartTime().atZone(zoneId).toLocalDateTime()
        );

        dto.setEndTime(
                session.getEndTime().atZone(zoneId).toLocalDateTime()
        );

        return dto;
    }

    public static BookingResponseDTO toBookingDTO(Booking booking) {

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getId());
        dto.setOfferingId(booking.getOffering().getId());
        dto.setCourseName(booking.getOffering().getCourse().getName());

        return dto;
    }
}
