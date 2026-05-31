package com.course_booking_api.service;

import com.course_booking_api.dto.CreateOfferingRequest;
import com.course_booking_api.entity.Course;
import com.course_booking_api.entity.Offering;
import com.course_booking_api.entity.Teacher;
import com.course_booking_api.exception.ResourceNotFoundException;
import com.course_booking_api.repository.CourseRepository;
import com.course_booking_api.repository.OfferingRepository;
import com.course_booking_api.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OfferingService {

    private final OfferingRepository offeringRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public OfferingService(OfferingRepository offeringRepository,
                           CourseRepository courseRepository,
                           TeacherRepository teacherRepository) {
        this.offeringRepository = offeringRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public Offering createOffering(CreateOfferingRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Offering offering = new Offering();
        offering.setCourse(course);
        offering.setTeacher(teacher);
        offering.setTimezone(request.getTimezone());

        return offeringRepository.save(offering);
    }

    public Offering getOffering(Long id){
        return offeringRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Offering not found with id:"+id));
    }

    public List<Offering> getAllOfferings() {
        return offeringRepository.findAll();
    }
}
