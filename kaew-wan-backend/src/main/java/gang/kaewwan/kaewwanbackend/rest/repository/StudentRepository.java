package gang.kaewwan.kaewwanbackend.rest.repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
