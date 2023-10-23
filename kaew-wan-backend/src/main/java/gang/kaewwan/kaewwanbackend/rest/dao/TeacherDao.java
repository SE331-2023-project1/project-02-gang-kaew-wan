package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import org.springframework.data.domain.Page;

import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    Integer getTeacherSize();

    Page<Teacher> getTeachers(Integer pageSize, Integer page);

    Teacher getTeacher(Long id);

    Teacher save(Teacher teacher);
    Teacher assignStudent(Long id ,List<Student> students);
}
