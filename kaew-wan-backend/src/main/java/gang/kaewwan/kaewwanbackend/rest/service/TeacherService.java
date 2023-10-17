package gang.kaewwan.kaewwanbackend.rest.service;

import org.springframework.data.domain.Page;

import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;

public interface TeacherService {
    Integer getTeacherSize();

    Page<Teacher> getTeachers(Integer pageSize, Integer page);

    Teacher getTeacher(Long id);

    Teacher save(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacher);
}
