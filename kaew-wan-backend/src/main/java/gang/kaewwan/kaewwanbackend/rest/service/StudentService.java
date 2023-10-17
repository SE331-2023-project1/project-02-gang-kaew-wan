package gang.kaewwan.kaewwanbackend.rest.service;

import org.springframework.data.domain.Page;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;

public interface StudentService {
    Integer getStudentSize();

    Page<Student> getStudents(Integer pageSize, Integer page);

    Page<Student> getStudents(Integer pageSize, Integer page, String keyword);

    Student getStudent(Long id);

    Student save(Student student);

    Student updateStudent(Long id, Student student);
}
