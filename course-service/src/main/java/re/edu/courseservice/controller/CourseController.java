package re.edu.courseservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.courseservice.model.Course;
import re.edu.courseservice.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        log.info("Fetching course with id: {}", courseId);
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<Void> enrollStudent(@PathVariable Long courseId) {
        log.info("Processing enrollment for course: {}", courseId);
        courseService.incrementEnrollment(courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        log.info("Creating new course: {}", course.getCourseName());
        return ResponseEntity.ok(courseService.createCourse(course));
    }
}
