package re.edu.courseservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.courseservice.model.Course;
import re.edu.courseservice.repository.CourseRepository;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course getCourse(Long id) {
        log.info("Fetching course with id: {}", id);
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void incrementEnrollment(Long courseId) {
        log.info("Incrementing enrollment for course: {}", courseId);
        Course course = getCourse(courseId);

        if (course.getEnrolledCount() >= course.getCapacity()) {
            throw new RuntimeException("Course is full");
        }

        course.setEnrolledCount(course.getEnrolledCount() + 1);
        courseRepository.save(course);
    }

    public Course createCourse(Course course) {
        log.info("Creating new course: {}", course.getCourseName());
        return courseRepository.save(course);
    }
}
