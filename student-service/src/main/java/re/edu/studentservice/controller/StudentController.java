package re.edu.studentservice.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.studentservice.model.Student;
import re.edu.studentservice.service.StudentService;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/enroll")
    public ResponseEntity<Student> enrollCourse(@RequestBody EnrollRequest request) {
        log.info("Enrollment request: studentId={}, courseId={}",
                request.getStudentId(), request.getCourseId());
        Student student = studentService.enrollCourse(
                request.getStudentId(),
                request.getCourseId()
        );
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        log.info("Fetching student with id: {}", id);
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        log.info("Creating new student: {}", student.getName());
        return ResponseEntity.ok(studentService.createStudent(student));
    }
}
@Data
class EnrollRequest {
    private Long studentId;
    private Long courseId;
}

