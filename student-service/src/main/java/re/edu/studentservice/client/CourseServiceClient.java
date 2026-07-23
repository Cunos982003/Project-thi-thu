package re.edu.studentservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", url = "http://localhost:8082")
public interface CourseServiceClient {

    @GetMapping("/api/courses/{courseId}")
    CourseDto getCourse(@PathVariable Long courseId);
}

