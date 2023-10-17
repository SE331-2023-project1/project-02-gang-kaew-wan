package gang.kaewwan.kaewwanbackend.rest.dao;

import org.springframework.data.domain.Page;

import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;

public interface TeacherDao {
    Integer getTeacherSize();

    Page<Teacher> getTeachers(Integer pageSize, Integer page);

    Teacher getTeacher(Long id);

    Teacher save(Teacher teacher);
}
