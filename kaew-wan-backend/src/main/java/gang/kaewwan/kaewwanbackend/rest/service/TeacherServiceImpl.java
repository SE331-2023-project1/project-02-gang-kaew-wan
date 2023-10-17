package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gang.kaewwan.kaewwanbackend.rest.dao.TeacherDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    final TeacherDao teacherDao;
    final TeacherRepository teacherRepository;

    @Override
    public Integer getTeacherSize() {
        return teacherDao.getTeacherSize();
    }

    @Override
    public Page<Teacher> getTeachers(Integer pageSize, Integer page) {
        return teacherDao.getTeachers(pageSize, page);
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherDao.getTeacher(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        if (teacherRepository.existsById(id)) {
            teacher.setId(id);
            return teacherDao.save(teacher);
        }
        return null;
    }

    @Override
    public Teacher assignStudent(Long id, List<Student> students) {
        return teacherDao.assignStudent(id, students);
    }

}
