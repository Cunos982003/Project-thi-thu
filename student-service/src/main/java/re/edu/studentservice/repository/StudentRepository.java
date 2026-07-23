package re.edu.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.studentservice.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
