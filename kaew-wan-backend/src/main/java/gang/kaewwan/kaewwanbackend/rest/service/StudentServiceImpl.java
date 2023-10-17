package gang.kaewwan.kaewwanbackend.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gang.kaewwan.kaewwanbackend.rest.dao.StudentDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    final StudentDao studentDao;

    @Override
    public Integer getStudentSize() {
        return studentDao.getStudentSize();
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page) {
        return studentDao.getStudents(pageSize, page);
    }

    @Override
    public Page<Student> getStudents(Integer pageSize, Integer page, String keyword) {
        return studentDao.getStudents(pageSize, page, keyword);
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.getStudent(id);
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

}
