package gang.kaewwan.kaewwanbackend.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {
    final StudentRepository studentRepository;

    @Override
    public Integer getStudentSize() {
        return Math.toIntExact(studentRepository.count());
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page) {
        return studentRepository.findAll(pageSize != null && page != null ? PageRequest.of(page - 1, pageSize)
                : PageRequest.of(0, getStudentSize()));
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page, String keyword) {
        return studentRepository
                .findByStudentIdContainingIgnoreCaseOrFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(keyword,
                        keyword, keyword, pageSize != null && page != null ? PageRequest.of(page - 1, pageSize)
                                : PageRequest.of(0, getStudentSize()));
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page, Long advisorId) {
        return studentRepository.findByTeacher_Id(advisorId, pageSize != null && page != null ? PageRequest.of(page - 1, pageSize)
                : PageRequest.of(0, getStudentSize()));
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page, Long advisorId, String keyword) {
        return studentRepository
                .findByStudentIdContainingIgnoreCaseOrFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndTeacher_Id(keyword,
                        keyword, keyword, advisorId ,pageSize != null && page != null ? PageRequest.of(page - 1, pageSize)
                                : PageRequest.of(0, getStudentSize()));
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

}
