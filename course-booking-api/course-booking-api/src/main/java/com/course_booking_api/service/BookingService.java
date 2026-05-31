package com.course_booking_api.service;

import com.course_booking_api.dto.BookingRequestDTO;
import com.course_booking_api.entity.Booking;
import com.course_booking_api.entity.Offering;
import com.course_booking_api.entity.Parent;
import com.course_booking_api.entity.Session;
import com.course_booking_api.exception.BookingConflictException;
import com.course_booking_api.exception.ResourceNotFoundException;
import com.course_booking_api.repository.BookingRepository;
import com.course_booking_api.repository.OfferingRepository;
import com.course_booking_api.repository.ParentRepository;
import com.course_booking_api.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ParentRepository parentRepository;
    private final OfferingRepository offeringRepository;
    private final SessionRepository sessionRepository;

    public BookingService(BookingRepository bookingRepository,
                          ParentRepository parentRepository,
                          OfferingRepository offeringRepository, SessionRepository sessionRepository) {
        this.bookingRepository = bookingRepository;
        this.parentRepository = parentRepository;
        this.offeringRepository = offeringRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Booking bookOffering(Long parentId, Long offeringId) {

        //Fetching Parent
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

       //Fetching Offering
        Offering offering = offeringRepository.findById(offeringId)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        //  Use entity relationship
        List<Session> newSessions = offering.getSessions();

        // Lock for concurrency
        List<Booking> bookings =
                bookingRepository.findByParentIdForUpdate(parentId);

        for (Booking booking : bookings) {

            List<Session> existingSessions =
                    booking.getOffering().getSessions();

            for (Session newSession : newSessions) {
                for (Session existingSession : existingSessions) {

                    if (isOverlapping(newSession, existingSession)) {
                        throw new BookingConflictException(  "Time conflict detected. Cannot book overlapping sessions.");
                    }
                }
            }
        }

        Booking newBooking = new Booking();
        newBooking.setParent(parent);
        newBooking.setOffering(offering);

        return bookingRepository.save(newBooking);


    }

    private boolean isOverlapping(Session s1, Session s2) {
        return s1.getStartTime().isBefore(s2.getEndTime()) &&
                s1.getEndTime().isAfter(s2.getStartTime());
    }

    public List<Booking> getBookingsByParent(Long parentId) {

        List<Booking> bookings = bookingRepository.findByParentId(parentId);
        if(bookings.isEmpty()){
            throw new ResourceNotFoundException("No bookings found for parentId:"+parentId);
        }
        return bookings;
    }


}