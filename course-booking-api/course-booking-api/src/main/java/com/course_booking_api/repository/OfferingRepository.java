package com.course_booking_api.repository;

import com.course_booking_api.entity.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferingRepository extends JpaRepository<Offering,Long> {
    List<Offering> findByTeacherId(Long teacherId);
}
