package gang.kaewwan.kaewwanbackend.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByStudentIdContainingIgnoreCaseOrFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(
            String studentId, String fname, String lname, Pageable pageRequest);
    Page<Student> findByTeacher_Id(Long advisorId,Pageable pageRequest );
    Page<Student> findByStudentIdContainingIgnoreCaseOrFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndTeacher_Id(
            String studentId, String fname, String lname, Long advisorId,  Pageable pageRequest);
}
