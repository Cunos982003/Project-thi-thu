package re.edu.studentservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.studentservice.client.CourseServiceClient;
import re.edu.studentservice.model.Student;
import re.edu.studentservice.repository.StudentRepository;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseServiceClient courseServiceClient;

    public Student enrollCourse(Long studentId, Long courseId) {
        log.info("Attempting to enroll student {} in course {}", studentId, courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Call Course Service via OpenFeign
        var course = courseServiceClient.getCourse(courseId);

        if (course.getCapacity() <= course.getEnrolledCount()) {
            throw new RuntimeException("Course is full - Capacity: " + course.getCapacity());
        }

        if (!student.getEnrolledCourses().contains(courseId)) {
            student.getEnrolledCourses().add(courseId);
            log.info("Student {} enrolled in course {}", studentId, courseId);
        }

        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
