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
            Teacher teacherData = this.getTeacher(id);
            if (teacher.getFname() == null) teacher.setFname(teacherData.getFname());
            if (teacher.getLname() == null) teacher.setFname(teacherData.getLname());
            if (teacher.getStudents() == null) teacher.setStudents(teacherData.getStudents());
            if (teacher.getUser() == null) teacher.setUser(teacherData.getUser());
            if (teacher.getAnnouncements() == null) teacher.setAnnouncements(teacherData.getAnnouncements());
            if (teacher.getReviews() == null) teacher.setReviews(teacherData.getReviews());
            if (teacher.getPosition() == null) teacher.setPosition(teacherData.getPosition());
            if (teacher.getImage() == null) teacher.setImage(teacherData.getImage());
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
