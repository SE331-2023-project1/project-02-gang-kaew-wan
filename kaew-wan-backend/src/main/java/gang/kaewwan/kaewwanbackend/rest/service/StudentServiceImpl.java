package gang.kaewwan.kaewwanbackend.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gang.kaewwan.kaewwanbackend.rest.dao.StudentDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    final StudentDao studentDao;
    final StudentRepository studentRepository;

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
    public Page<Student> getStudentsByAdvisor(Integer pageSize, Integer page, Long advisorId) {
        return studentDao.getStudents(pageSize, page, advisorId);
    }

    @Override
    public Page<Student> getStudentsByAdvisor(Integer pageSize, Integer page, Long advisorId, String keyword) {
        return studentDao.getStudents(pageSize, page, advisorId,keyword);
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.getStudent(id);
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            Student studentData = this.getStudent(id);
            if(student.getFname() == null) student.setFname(studentData.getFname());
            if(student.getLname() == null) student.setLname(studentData.getLname());
            if(student.getImage() == null) student.setImage(studentData.getImage());
            if(student.getDepartment() == null) student.setDepartment(studentData.getDepartment());
            if(student.getTeacher() == null ) student.setTeacher(studentData.getTeacher());
            if(student.getReview() == null) student.setReview(studentData.getReview());
            if(student.getUser() == null) student.setUser(studentData.getUser());
            if(student.getStudentId() == null) student.setStudentId(studentData.getStudentId());
            student.setId(id);
            return studentDao.save(student);
        }
        return null;
    }

}
