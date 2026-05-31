package com.course_booking_api.controller;

import com.course_booking_api.dto.SessionResponseDTO;
import com.course_booking_api.entity.Course;
import com.course_booking_api.entity.Offering;
import com.course_booking_api.entity.Session;
import com.course_booking_api.entity.Teacher;
import com.course_booking_api.repository.CourseRepository;
import com.course_booking_api.repository.OfferingRepository;
import com.course_booking_api.repository.SessionRepository;
import com.course_booking_api.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final OfferingRepository offeringRepository;
    private final TeacherRepository teacherRepository;
    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;

    public TeacherController(OfferingRepository offeringRepository,
                             TeacherRepository teacherRepository,
                             SessionRepository sessionRepository, CourseRepository courseRepository) {
        this.offeringRepository = offeringRepository;
        this.teacherRepository = teacherRepository;
        this.sessionRepository = sessionRepository;
        this.courseRepository = courseRepository;
    }

    // Create Offering
    @PostMapping("/offering")
    public Offering createOffering(@RequestParam Long teacherId,
                                   @RequestParam String courseName,
                                   @RequestParam String timezone) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = new Course();
        course.setName(courseName);

        course  = courseRepository.save(course);

        Offering offering = new Offering();
        offering.setTeacher(teacher);
        offering.setCourse(course);
        offering.setTimezone(timezone);

        return offeringRepository.save(offering);
    }

    // Add Session to Offering
    @PostMapping("/session")
    public SessionResponseDTO addSession(@RequestParam Long offeringId,
                                         @RequestParam String startTime,

                                         @RequestParam String endTime) {
        //Fetching Offering
        Offering offering = offeringRepository.findById(offeringId)
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        //Parsing Time
        LocalDateTime start;
        LocalDateTime end;

        try{
            start =LocalDateTime.parse(startTime);
            end = LocalDateTime.parse(endTime);
        }catch(Exception ex){
            throw new RuntimeException("Invalid date format.Use: yyyy-MM-ddTHH:mm:ss");
        }

        //Adding Validation
        if(end.isBefore(start)){
            throw new RuntimeException("End time must not start before");
        }
        //Creating Session
        Session session = new Session();
        session.setOffering(offering);

        session.setStartTime(LocalDateTime.parse(startTime));
        session.setEndTime(LocalDateTime.parse(endTime));

       Session saved =  sessionRepository.save(session);

       //Retunring DTO instead of direct Entity
        SessionResponseDTO response = new SessionResponseDTO();
        response.setId(saved.getId());
        response.setStartTime(saved.getStartTime());
        response.setEndTime(saved.getEndTime());

        return response;
    }

    //  View teacher offerings
    @GetMapping("/offerings/{teacherId}")
    public List<Offering> getTeacherOfferings(@PathVariable Long teacherId) {
        return offeringRepository.findByTeacherId(teacherId);
    }
}